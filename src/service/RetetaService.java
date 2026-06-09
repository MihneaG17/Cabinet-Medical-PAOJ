package service;

import models.Reteta;

import java.util.ArrayList;
import java.util.List;

import db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class RetetaService implements GenericService<Reteta> {
    private static RetetaService instance;

    private RetetaService() {
    }

    public static RetetaService getInstance() {
        if (instance == null) {
            instance = new RetetaService();
        }
        return instance;
    }

    @Override
    public void create(Reteta entitate) {
        String sqlReteta = "INSERT INTO Retete (id_medic, id_pacient, data_emiterii) VALUES (?, ?, ?)";
        String sqlLegatura = "INSERT INTO Retete_Medicamente (id_reteta, id_medicament) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtReteta = conn.prepareStatement(sqlReteta, new String[]{"id_reteta"})) {
                pstmtReteta.setInt(1, entitate.getMedic().getIdMedic());
                pstmtReteta.setInt(2, entitate.getPacient().getIdPacient());
                pstmtReteta.setDate(3, java.sql.Date.valueOf(entitate.getDataEmiterii()));

                int rowsAffected = pstmtReteta.executeUpdate();

                if (rowsAffected > 0) {
                    try (ResultSet rsKeys = pstmtReteta.getGeneratedKeys()) {
                        if (rsKeys.next()) {
                            int idRetetaGenerat = rsKeys.getInt(1);
                            entitate.setIdReteta(idRetetaGenerat);

                            if (!entitate.getMedicamente().isEmpty()) {
                                try (PreparedStatement pstmtLegatura = conn.prepareStatement(sqlLegatura)) {
                                    for (models.Medicament m : entitate.getMedicamente()) {
                                        pstmtLegatura.setInt(1, idRetetaGenerat);
                                        pstmtLegatura.setInt(2, m.getIdMedicament());
                                        pstmtLegatura.addBatch();
                                    }
                                    pstmtLegatura.executeBatch();
                                }
                            }

                            conn.commit();
                            System.out.println("(RetetaService) Reteta si medicamentele au fost salvate cu succes!");
                        }
                    }
                }
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("(RetetaService) Eroare la inserarea retetei si a medicamentelor.");
            e.printStackTrace();
        }
    }

    @Override
    public Reteta read(int id) {
        return null;
    }

    @Override
    public void update(Reteta entitate) {
        System.out.println("(RetetaService) Update-ul nu este suportat complet pentru retete.");
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Retete WHERE id_reteta = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(RetetaService) Reteta cu ID-ul " + id + " a fost stearsa.");
            } else {
                System.out.println("(RetetaService) Nu s-a gasit reteta cu acest ID.");
            }
        } catch (SQLException e) {
            System.err.println("(RetetaService) Eroare la stergerea retetei.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Reteta> readAll() {
        List<Reteta> lista = new ArrayList<>();

        List<models.Medic> medici = MedicService.getInstance().readAll();
        List<models.Pacient> pacienti = PacientService.getInstance().readAll();
        List<models.Medicament> medicamente = MedicamentService.getInstance().readAll();

        String sqlReteta = "SELECT id_reteta, id_medic, id_pacient, data_emiterii FROM Retete";
        String sqlLegatura = "SELECT id_medicament FROM Retete_Medicamente WHERE id_reteta = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlReteta);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idReteta = rs.getInt("id_reteta");
                int idMedic = rs.getInt("id_medic");
                int idPacient = rs.getInt("id_pacient");
                Date dataEmiterii = rs.getDate("data_emiterii");

                models.Medic medicGasit = null;
                for (models.Medic m : medici) {
                    if (m.getIdMedic() == idMedic) {
                        medicGasit = m;
                        break;
                    }
                }

                models.Pacient pacientGasit = null;
                for (models.Pacient p : pacienti) {
                    if (p.getIdPacient() == idPacient) {
                        pacientGasit = p;
                        break;
                    }
                }

                if (medicGasit != null && pacientGasit != null) {
                    Reteta reteta = new Reteta(idReteta, medicGasit, pacientGasit, dataEmiterii.toLocalDate());

                    try (PreparedStatement pstmtLegatura = conn.prepareStatement(sqlLegatura)) {
                        pstmtLegatura.setInt(1, idReteta);
                        try (ResultSet rsLegatura = pstmtLegatura.executeQuery()) {
                            while (rsLegatura.next()) {
                                int idMedicament = rsLegatura.getInt("id_medicament");
                                for (models.Medicament med : medicamente) {
                                    if (med.getIdMedicament() == idMedicament) {
                                        reteta.addMedicament(med);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    lista.add(reteta);
                }
            }
        } catch (SQLException e) {
            System.err.println("(RetetaService) Eroare la citirea retetelor.");
            e.printStackTrace();
        }
        return lista;
    }
}
