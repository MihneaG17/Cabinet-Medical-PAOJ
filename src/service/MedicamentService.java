package service;

import db.DatabaseManager;
import models.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentService implements GenericService<Medicament> {
    private static MedicamentService instance;

    private MedicamentService() {}

    public static MedicamentService getInstance() {
        if (instance == null) {
            instance = new MedicamentService();
        }
        return instance;
    }

    @Override
    public void create(Medicament entitate) {
        String sql = "INSERT INTO Medicamente (nume, dozaj, substanta_activa) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getNume());
            pstmt.setInt(2, entitate.getDozaj());
            pstmt.setString(3, entitate.getSubstantaActiva());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(MedicamentService) Medicamentul a fost adaugat cu succes.");
            }
        } catch (SQLException e) {
            System.err.println("(MedicamentService) Eroare la adaugarea medicamentului.");
            e.printStackTrace();
        }
    }

    @Override
    public Medicament read(int id) {
        return null;
    }

    @Override
    public void update(Medicament entitate) {
        String sql = "UPDATE Medicamente SET nume = ?, dozaj = ?, substanta_activa = ? WHERE id_medicament = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getNume());
            pstmt.setInt(2, entitate.getDozaj());
            pstmt.setString(3, entitate.getSubstantaActiva());
            pstmt.setInt(4, entitate.getIdMedicament());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(MedicamentService) Medicamentul a fost actualizat cu succes.");
            }
        } catch (SQLException e) {
            System.err.println("(MedicamentService) Eroare la actualizarea medicamentului.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Medicamente WHERE id_medicament = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(MedicamentService) Medicamentul cu ID-ul " + id + " a fost sters.");
            } else {
                System.out.println("(MedicamentService) Nu s-a gasit medicament cu acest ID.");
            }
        } catch (SQLException e) {
            System.err.println("(MedicamentService) Eroare la stergerea medicamentului.");
            e.printStackTrace();
        }
    }

    public List<Medicament> readAll() {
        List<Medicament> medicamente = new ArrayList<>();
        String sql = "SELECT * FROM Medicamente";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Medicament m = new Medicament(
                        rs.getInt("id_medicament"),
                        rs.getString("nume"),
                        rs.getInt("dozaj"),
                        rs.getString("substanta_activa")
                );
                medicamente.add(m);
            }
        } catch (SQLException e) {
            System.err.println("(MedicamentService) Eroare la citirea medicamentelor.");
            e.printStackTrace();
        }
        return medicamente;
    }
}