package service;

import db.DatabaseManager;
import models.ServiciuMedical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiciuMedicalService {
    private static ServiciuMedicalService instance;

    private ServiciuMedicalService() {}

    public static ServiciuMedicalService getInstance() {
        if (instance == null) {
            instance = new ServiciuMedicalService();
        }
        return instance;
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