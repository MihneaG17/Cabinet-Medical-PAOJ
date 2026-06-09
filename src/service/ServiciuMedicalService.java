package service;

import db.DatabaseManager;
import models.ServiciuMedical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiciuMedicalService implements GenericService<ServiciuMedical> {
    private static ServiciuMedicalService instance;

    private ServiciuMedicalService() {}

    public static ServiciuMedicalService getInstance() {
        if (instance == null) {
            instance = new ServiciuMedicalService();
        }
        return instance;
    }

    @Override
    public void create(ServiciuMedical entitate) {
        String sql = "INSERT INTO Servicii_Medicale (nume_serviciu, pret, durata_minute) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getNumeServiciu());
            pstmt.setDouble(2, entitate.getPret());
            pstmt.setInt(3, entitate.getDurataMinute());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(ServiciuMedicalService) Serviciul a fost adaugat cu succes.");
            }
        } catch (SQLException e) {
            System.err.println("(ServiciuMedicalService) Eroare la adaugarea serviciului.");
            e.printStackTrace();
        }
    }

    @Override
    public ServiciuMedical read(int id) {
        return null;
    }

    @Override
    public void update(ServiciuMedical entitate) {
        String sql = "UPDATE Servicii_Medicale SET nume_serviciu = ?, pret = ?, durata_minute = ? WHERE id_serviciu = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getNumeServiciu());
            pstmt.setDouble(2, entitate.getPret());
            pstmt.setInt(3, entitate.getDurataMinute());
            pstmt.setInt(4, entitate.getIdServiciu());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(ServiciuMedicalService) Serviciul a fost actualizat cu succes.");
            }
        } catch (SQLException e) {
            System.err.println("(ServiciuMedicalService) Eroare la actualizarea serviciului.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Servicii_Medicale WHERE id_serviciu = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(ServiciuMedicalService) Serviciul cu ID-ul " + id + " a fost sters.");
            } else {
                System.out.println("(ServiciuMedicalService) Nu s-a gasit serviciu cu acest ID.");
            }
        } catch (SQLException e) {
            System.err.println("(ServiciuMedicalService) Eroare la stergerea serviciului.");
            e.printStackTrace();
        }
    }

    public List<ServiciuMedical> readAll() {
        List<ServiciuMedical> servicii = new ArrayList<>();
        String sql = "SELECT * FROM Servicii_Medicale";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ServiciuMedical sm = new ServiciuMedical(
                        rs.getInt("id_serviciu"),
                        rs.getString("nume_serviciu"),
                        rs.getDouble("pret"),
                        rs.getInt("durata_minute")
                );
                servicii.add(sm);
            }
        } catch (SQLException e) {
            System.err.println("(ServiciuMedicalService) Eroare la citirea serviciilor medicale.");
            e.printStackTrace();
        }
        return servicii;
    }
}