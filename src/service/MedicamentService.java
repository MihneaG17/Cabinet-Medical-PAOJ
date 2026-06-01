package service;

import db.DatabaseManager;
import models.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentService {
    private static MedicamentService instance;

    private MedicamentService() {}

    public static MedicamentService getInstance() {
        if (instance == null) {
            instance = new MedicamentService();
        }
        return instance;
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