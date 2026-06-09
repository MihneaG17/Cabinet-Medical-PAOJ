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
        String sql = "INSERT INTO Retete (id_medic, id_pacient, data_emiterii) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entitate.getMedic().getIdMedic());
            pstmt.setInt(2, entitate.getPacient().getIdPacient());
            pstmt.setDate(3, Date.valueOf(entitate.getDataEmiterii()));

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(RetetaService) Reteta a fost salvata cu succes in baza de date.");
            }
        } catch (SQLException e) {
            System.err.println("(RetetaService) Eroare la inserarea retetei.");
            e.printStackTrace();
        }
    }

    @Override
    public Reteta read(int id) {
        return null; // Not requested to be implemented based on context, but left as null
    }

    @Override
    public void update(Reteta entitate) {
        // Not really common to update prescriptions, but since it's required:
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
        return lista;
    }
}
