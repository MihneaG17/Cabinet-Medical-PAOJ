package service;

import db.DatabaseManager;
import models.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaService {
    private static SalaService instance;

    private SalaService() {}

    public static SalaService getInstance() {
        if (instance == null) {
            instance = new SalaService();
        }
        return instance;
    }

    public List<Sala> readAll() {
        List<Sala> sali = new ArrayList<>();
        String sql = "SELECT * FROM Sali";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Sala s = new Sala(
                        rs.getInt("nr_sala"),
                        rs.getInt("etaj"),
                        rs.getString("tip_sala")
                );
                sali.add(s);
            }
        } catch (SQLException e) {
            System.err.println("(SalaService) Eroare la citirea salilor.");
            e.printStackTrace();
        }
        return sali;
    }
}