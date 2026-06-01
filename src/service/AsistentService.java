package service;

import db.DatabaseManager;
import models.Asistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//singleton
public class AsistentService implements GenericService<Asistent> {
    private static AsistentService instance;

    private AsistentService() {
    }

    public static AsistentService getInstance() {
        if (instance == null) {
            instance = new AsistentService();
        }
        return instance;
    }

    @Override
    public void create(Asistent entitate) {
        String sql = "INSERT INTO Asistenti (cnp, nume, prenume, nr_telefon, salariu, tura, data_angajarii, grad_profesional, poate_recolta_probe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getCnp());
            pstmt.setString(2, entitate.getNume());
            pstmt.setString(3, entitate.getPrenume());
            pstmt.setString(4, entitate.getNrTelefon());
            pstmt.setDouble(5, entitate.getSalariu());
            pstmt.setString(6, entitate.getTura());
            pstmt.setDate(7, java.sql.Date.valueOf(entitate.getDataAngajarii()));
            pstmt.setString(8, entitate.getGradProfesional());
            pstmt.setInt(9, entitate.isPoateRecolta() ? 1 : 0);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("(AsistentService) Asistentul " + entitate.getNume() + " a fost salvat cu succes în baza de date!");
            }
        } catch (SQLException e) {
            System.err.println("(AsistentService) Eroare la inserarea asistentului in baza de date");
            e.printStackTrace();
        }
    }

    @Override
    public List<Asistent> readAll() {
        List<Asistent> asistenti = new ArrayList<>();
        String sql = "SELECT * FROM Asistenti";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_asistent");
                String cnp = rs.getString("cnp");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String telefon = rs.getString("nr_telefon");
                double salariu = rs.getDouble("salariu");
                String tura = rs.getString("tura");
                java.sql.Date sqlDate = rs.getDate("data_angajarii");
                LocalDate dataAngajarii = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                String grad = rs.getString("grad_profesional");
                boolean poateRecolta = rs.getInt("poate_recolta_probe") == 1;

                Asistent a = new Asistent(id, nume, prenume, cnp, telefon, salariu, tura, dataAngajarii, grad, poateRecolta);
                asistenti.add(a);
            }

        } catch (SQLException e) {
            System.err.println("(AsistentService) Eroare la citirea asistenților.");
            e.printStackTrace();
        }
        return asistenti;
    }

    public Asistent cautaDupaCnp(String cnpCautat) {
        String sql = "SELECT * FROM Asistenti WHERE cnp = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cnpCautat);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    java.sql.Date sqlDate = rs.getDate("data_angajarii");
                    LocalDate dataAngajarii = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                    boolean poateRecolta = rs.getInt("poate_recolta_probe") == 1;

                    return new Asistent(
                            rs.getInt("id_asistent"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getString("cnp"),
                            rs.getString("nr_telefon"),
                            rs.getDouble("salariu"),
                            rs.getString("tura"),
                            dataAngajarii,
                            rs.getString("grad_profesional"),
                            poateRecolta
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("(AsistentService) Eroare la cautarea asistentului dupa CNP");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Asistent read(int id) {
        return null;
    }

    @Override
    public void update(Asistent entitate) {
    }

    @Override
    public void delete(int id) {
    }
}