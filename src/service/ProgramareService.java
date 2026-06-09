package service;

import db.DatabaseManager;
import models.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgramareService implements GenericService<Programare> {
    private static ProgramareService instance;

    private ProgramareService() {}

    public static ProgramareService getInstance() {
        if (instance == null) {
            instance = new ProgramareService();
        }
        return instance;
    }

    @Override
    public void create(Programare entitate) {
        String sql = "INSERT INTO Programari (id_pacient, id_medic, id_asistent, id_serviciu, nr_sala, data_si_ora, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entitate.getPacient().getIdPacient());
            pstmt.setInt(2, entitate.getMedic().getIdMedic());

            if (entitate.getAsistent() != null) {
                pstmt.setInt(3, entitate.getAsistent().getIdAsistent());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }

            pstmt.setInt(4, entitate.getServiciu().getIdServiciu());
            pstmt.setInt(5, entitate.getSala().getNrSala());
            pstmt.setTimestamp(6, Timestamp.valueOf(entitate.getDataSiOra()));
            pstmt.setString(7, entitate.getStatus());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(ProgramareService) Programarea a fost salvata cu succes in baza de date");
            }

        } catch (SQLException e) {
            System.err.println("(ProgramareService) Eroare la inserarea programarii.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Programare> readAll() {
        List<Programare> programari = new ArrayList<>();

        List<Pacient> totiPacientii = PacientService.getInstance().readAll();
        List<Medic> totiMedicii = MedicService.getInstance().readAll();
        List<Asistent> totiAsistentii = AsistentService.getInstance().readAll();
        List<ServiciuMedical> toateServiciile = ServiciuMedicalService.getInstance().readAll();
        List<Sala> toateSalile = SalaService.getInstance().readAll();

        String sql = "SELECT * FROM Programari";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idProgramare = rs.getInt("id_programare");
                int idPacient = rs.getInt("id_pacient");
                int idMedic = rs.getInt("id_medic");

                int idAsistent = rs.getInt("id_asistent");
                boolean asistentIsNull = rs.wasNull();

                int idServiciu = rs.getInt("id_serviciu");
                int nrSala = rs.getInt("nr_sala");

                Timestamp ts = rs.getTimestamp("data_si_ora");
                LocalDateTime dataOra = (ts != null) ? ts.toLocalDateTime() : null;
                String status = rs.getString("status");

                Pacient pacient = totiPacientii.stream().filter(p -> p.getIdPacient() == idPacient).findFirst().orElse(null);
                Medic medic = totiMedicii.stream().filter(m -> m.getIdMedic() == idMedic).findFirst().orElse(null);
                ServiciuMedical serviciu = toateServiciile.stream().filter(s -> s.getIdServiciu() == idServiciu).findFirst().orElse(null);
                Sala sala = toateSalile.stream().filter(s -> s.getNrSala() == nrSala).findFirst().orElse(null);

                Asistent asistent = null;
                if (!asistentIsNull) {
                    asistent = totiAsistentii.stream().filter(a -> a.getIdAsistent() == idAsistent).findFirst().orElse(null);
                }

                Programare programare = new Programare(idProgramare, pacient, medic, asistent, serviciu, sala, dataOra);
                programare.setStatus(status);

                programari.add(programare);
            }

        } catch (SQLException e) {
            System.err.println("(ProgramareService) Eroare la citirea programarilor.");
            e.printStackTrace();
        }
        return programari;
    }

    @Override
    public Programare read(int id) {
        return null;
    }

    @Override
    public void update(Programare entitate) {
        String sql = "UPDATE Programari SET status = ? WHERE id_programare = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getStatus());
            pstmt.setInt(2, entitate.getIdProgramare());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("(ProgramareService) Eroare la actualizarea programarii.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Programari WHERE id_programare = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(ProgramareService) Programarea cu ID-ul " + id + " a fost stearsa din baza de date.");
            } else {
                System.out.println("(ProgramareService) Nu s-a gasit nicio programare cu acest ID pentru a fi stearsa.");
            }

        } catch (SQLException e) {
            System.err.println("(ProgramareService) Eroare la stergerea programarii.");
            e.printStackTrace();
        }
    }
}