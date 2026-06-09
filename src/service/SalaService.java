package service;

import db.DatabaseManager;
import models.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaService implements GenericService<Sala> {
    private static SalaService instance;

    private SalaService() {}

    public static SalaService getInstance() {
        if (instance == null) {
            instance = new SalaService();
        }
        return instance;
    }

    @Override
    public void create(Sala entitate) {
        String sql = "INSERT INTO Sali (etaj, tip_sala) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entitate.getEtaj());
            pstmt.setString(2, entitate.getTipSala());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(SalaService) Sala a fost adaugata cu succes.");
            }
        } catch (SQLException e) {
            System.err.println("(SalaService) Eroare la adaugarea salii.");
            e.printStackTrace();
        }
    }

    @Override
    public Sala read(int id) {
        return null;
    }

    @Override
    public void update(Sala entitate) {
        String sql = "UPDATE Sali SET etaj = ?, tip_sala = ? WHERE nr_sala = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entitate.getEtaj());
            pstmt.setString(2, entitate.getTipSala());
            pstmt.setInt(3, entitate.getNrSala());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(SalaService) Sala a fost actualizata cu succes.");
            }
        } catch (SQLException e) {
            System.err.println("(SalaService) Eroare la actualizarea salii.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Sali WHERE nr_sala = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(SalaService) Sala cu numarul " + id + " a fost stearsa.");
            } else {
                System.out.println("(SalaService) Nu s-a gasit sala cu acest numar.");
            }
        } catch (SQLException e) {
            System.err.println("(SalaService) Eroare la stergerea salii.");
            e.printStackTrace();
        }
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