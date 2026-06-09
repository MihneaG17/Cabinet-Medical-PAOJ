package service;

import db.DatabaseManager;
import models.Pacient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//singleton
public class PacientService implements GenericService<Pacient> {
    private static PacientService instance;

    private PacientService() {
    }

    public static PacientService getInstance() {
        if (instance == null) {
            instance = new PacientService();
        }
        return instance;
    }

    @Override
    public void create(Pacient entitate) {
        String sql = "INSERT INTO Pacienti (cnp, nume, prenume, nr_telefon, grupa_sanguina, parola, alergii) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getCnp());
            pstmt.setString(2, entitate.getNume());
            pstmt.setString(3, entitate.getPrenume());
            pstmt.setString(4, entitate.getNrTelefon());
            pstmt.setString(5, entitate.getGrupaSanguina());
            pstmt.setString(6, entitate.getParola());

            String alergiiString = String.join(",", entitate.getIstoricAlergii());
            pstmt.setString(7, alergiiString);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("(PacientService) Pacientul " + entitate.getNume() + " a fost salvat cu succes în baza de date!");
            }
        } catch (SQLException e) {
            System.err.println("(PacientService) Eroare la inserarea pacientului in baza de date");
            e.printStackTrace();
        }
    }

    @Override
    public List<Pacient> readAll() {
        List<Pacient> pacienti = new ArrayList<>();
        String sql = "SELECT * FROM Pacienti";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_pacient");
                String cnp = rs.getString("cnp");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String telefon = rs.getString("nr_telefon");
                String grupa = rs.getString("grupa_sanguina");
                String parola = rs.getString("parola");

                String alergiiStr = rs.getString("alergii");
                List<String> listaAlergii = new ArrayList<>();
                if (alergiiStr != null && !alergiiStr.trim().isEmpty()) {
                    listaAlergii = new ArrayList<>(Arrays.asList(alergiiStr.split(",")));
                }

                Pacient p = new Pacient(id, nume, prenume, cnp, telefon, grupa, listaAlergii, parola);
                pacienti.add(p);
            }

        } catch (SQLException e) {
            System.err.println("(PacientService) Eroare la citirea pacienților.");
            e.printStackTrace();
        }
        return pacienti;
    }

    @Override
    public Pacient read(int id) {
        return null;
    }

    @Override
    public void update(Pacient entitate) {
        String sql = "UPDATE Pacienti SET nume = ?, prenume = ?, nr_telefon = ?, grupa_sanguina = ?, parola = ?, alergii = ? WHERE id_pacient = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getNume());
            pstmt.setString(2, entitate.getPrenume());
            pstmt.setString(3, entitate.getNrTelefon());
            pstmt.setString(4, entitate.getGrupaSanguina());
            pstmt.setString(5, entitate.getParola());

            String alergiiString = String.join(",", entitate.getIstoricAlergii());
            pstmt.setString(6, alergiiString);

            pstmt.setInt(7, entitate.getIdPacient());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(PacientService) Pacientul a fost actualizat cu succes.");
            }
        } catch (SQLException e) {
            System.err.println("(PacientService) Eroare la actualizarea pacientului.");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Pacienti WHERE id_pacient = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(PacientService) Pacientul cu ID-ul " + id + " a fost sters din baza de date.");
            } else {
                System.out.println("(PacientService) Nu s-a gasit pacient cu acest ID pentru a fi sters.");
            }
        } catch (SQLException e) {
            System.err.println("(PacientService) Eroare la stergerea pacientului.");
            e.printStackTrace();
        }
    }

    public Pacient cautaDupaCnp(String cnpCautat) {
        String sql = "SELECT * FROM Pacienti WHERE cnp = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cnpCautat);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String alergiiStr = rs.getString("alergii");
                    List<String> listaAlergii = new ArrayList<>();
                    if (alergiiStr != null && !alergiiStr.trim().isEmpty()) {
                        listaAlergii = new ArrayList<>(Arrays.asList(alergiiStr.split(",")));
                    }

                    return new Pacient(
                            rs.getInt("id_pacient"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getString("cnp"),
                            rs.getString("nr_telefon"),
                            rs.getString("grupa_sanguina"),
                            listaAlergii,
                            rs.getString("parola")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("(PacientService) Eroare la cautarea pacientului dupa CNP");
            e.printStackTrace();
        }
        return null;
    }
}