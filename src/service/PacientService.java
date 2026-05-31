package service;

import db.DatabaseManager;
import models.Pacient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//singleton
public class PacientService implements GenericService<Pacient>{
    private static PacientService instance;

    private PacientService() {
    }

    //metoda de acces la instanta
    public static PacientService getInstance() {
        if(instance==null) {
            instance=new PacientService();
        }
        return instance;
    }

    //operatiile CRUD
    @Override
    public void create(Pacient entitate) {
        String sql="INSERT INTO Pacienti (cnp, nume, prenume, nr_telefon, grupa_sanguina, parola) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn=DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt=conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getCnp());
            pstmt.setString(2, entitate.getNume());
            pstmt.setString(3, entitate.getPrenume());
            pstmt.setString(4, entitate.getNrTelefon());
            pstmt.setString(5, entitate.getGrupaSanguina());
            pstmt.setString(6, entitate.getParola());

            int rowsAffected=pstmt.executeUpdate();

            if(rowsAffected>0) {
                System.out.println("(PacientService) Pacientul \" + entity.getNume() + \" a fost salvat cu succes în baza de date!");
            }
        }
        catch (SQLException e) {
            System.err.println("(PacientService) Eroare la inserarea pacientului in baza de date");
        }
    }

    @Override
    public List<Pacient> readAll() {
        List<Pacient> pacienti=new ArrayList<>();
        String sql="SELECT * FROM Pacienti";

        try(Connection conn=DatabaseManager.getInstance().getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery()) {

            while(rs.next()) {
                int id = rs.getInt("id_pacient");
                String cnp = rs.getString("cnp");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String telefon = rs.getString("nr_telefon");
                String grupa = rs.getString("grupa_sanguina");
                String parola = rs.getString("parola");

                Pacient p = new Pacient(id, nume, prenume, cnp, telefon, grupa, new ArrayList<>(), parola);
                pacienti.add(p);
            }

        } catch (SQLException e) {
            System.err.println("(PacientService) Eroare la citirea pacienților.");
            e.getMessage();
        }
        return pacienti;
    }

    @Override
    public Pacient read(int id) {
        return null;
    }

    @Override
    public void update(Pacient entitate) {

    }

    @Override
    public void delete(int id) {

    }

    public Pacient cautaDupaCnp(String cnpCautat) {
        String sql="SELECT * FROM Pacienti WHERE cnp = ?";

        try (Connection conn=DatabaseManager.getInstance().getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql)) {
            pstmt.setString(1, cnpCautat);

            try (ResultSet rs=pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Pacient(
                            rs.getInt("id_pacient"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getString("cnp"),
                            rs.getString("nr_telefon"),
                            rs.getString("grupa_sanguina"),
                            new ArrayList<>(),
                            rs.getString("parola")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("(PacientService) Eroare la cautarea pacientului dupa CNP");
            System.out.println(e.getMessage());
        }
        return null;
    }
}
