package service;

import db.DatabaseManager;
import models.Medic;
import models.Pacient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicService implements GenericService<Medic>{
    private static MedicService instance;

    private MedicService() {
    }

    //metoda de acces la instanta
    public static MedicService getInstance() {
        if(instance==null) {
            instance=new MedicService();
        }
        return instance;
    }

    //operatiile CRUD
    @Override
    public void create(Medic entitate) {
        String sql="INSERT INTO Medici (cnp, nume, prenume, nr_telefon, salariu, tura, data_angajarii, specializare, cost_consultatie, cod_parafa, parola) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn=DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt=conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getCnp());
            pstmt.setString(2, entitate.getNume());
            pstmt.setString(3, entitate.getPrenume());
            pstmt.setString(4, entitate.getNrTelefon());
            pstmt.setDouble(5, entitate.getSalariu());
            pstmt.setString(6, entitate.getTura());
            pstmt.setDate(7, java.sql.Date.valueOf(entitate.getDataAngajarii()));
            pstmt.setString(8, entitate.getSpecializare());
            pstmt.setDouble(9, entitate.getCostConsultatie());
            pstmt.setString(10, entitate.getCodParafa());
            pstmt.setString(11, entitate.getParola());

            int rowsAffected=pstmt.executeUpdate();

            if(rowsAffected>0) {
                System.out.println("(MedicService) Medicul " + entitate.getNume() + " a fost salvat cu succes în baza de date!");
            }
        }
        catch (SQLException e) {
            System.err.println("(MedicService) Eroare la inserarea medicului in baza de date");
            e.printStackTrace();
        }
    }

    @Override
    public List<Medic> readAll() {
        List<Medic> medici=new ArrayList<>();
        String sql="SELECT * FROM Medici";

        try(Connection conn=DatabaseManager.getInstance().getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery()) {

            while(rs.next()) {
                // AM CORECTAT: Numele coloanelor din Oracle și tipurile de date din Java
                int id = rs.getInt("id_medic");
                String cnp = rs.getString("cnp");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String telefon = rs.getString("nr_telefon");
                double salariu = rs.getDouble("salariu");
                String tura = rs.getString("tura");
                java.sql.Date sqlDate = rs.getDate("data_angajarii");
                LocalDate dataAngajarii = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                String specializare = rs.getString("specializare");
                double cost_consult = rs.getDouble("cost_consultatie");
                String cod_parafa = rs.getString("cod_parafa");
                String parola = rs.getString("parola");

                Medic m = new Medic(id, nume, prenume, cnp, telefon, salariu, tura, dataAngajarii, specializare, cost_consult, cod_parafa, parola);
                medici.add(m);
            }

        } catch (SQLException e) {
            System.err.println("(MediciService) Eroare la citirea medicilor.");
            e.printStackTrace();
        }
        return medici;
    }

    @Override
    public Medic read(int id) {
        return null;
    }

    @Override
    public void update(Medic entitate) {

    }

    @Override
    public void delete(int id) {

    }

    public Medic cautaDupaCnp(String cnpCautat) {
        String sql = "SELECT * FROM Medici WHERE cnp = ?";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cnpCautat);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    java.sql.Date sqlDate = rs.getDate("data_angajarii");
                    LocalDate dataAngajarii = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                    return new Medic(
                            rs.getInt("id_medic"),
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getString("cnp"),
                            rs.getString("nr_telefon"),
                            rs.getDouble("salariu"),
                            rs.getString("tura"),
                            dataAngajarii,
                            rs.getString("specializare"),
                            rs.getDouble("cost_consultatie"),
                            rs.getString("cod_parafa"),
                            rs.getString("parola")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("(MedicService) Eroare la cautarea medicului dupa CNP");
            e.printStackTrace();
        }
        return null;
    }
}
