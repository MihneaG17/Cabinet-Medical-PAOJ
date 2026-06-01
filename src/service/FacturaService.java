package service;

import db.DatabaseManager;
import models.Factura;
import models.Programare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacturaService implements GenericService<Factura> {
    private static FacturaService instance;

    private FacturaService() {}

    public static FacturaService getInstance() {
        if (instance == null) {
            instance = new FacturaService();
        }
        return instance;
    }

    @Override
    public void create(Factura entitate) {
        String sql = "INSERT INTO Facturi (pret, data_emiterii, id_programare, status_plata) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, entitate.getPret());
            pstmt.setDate(2, java.sql.Date.valueOf(entitate.getDataEmiterii()));
            pstmt.setInt(3, entitate.getProgramare().getIdProgramare());
            pstmt.setString(4, entitate.getStatusPlata());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("(FacturaService) Factura a fost salvata cu succes în baza de date");
            }

        } catch (SQLException e) {
            System.err.println("(FacturaService) Eroare la inserarea facturii.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Factura> readAll() {
        List<Factura> facturi = new ArrayList<>();

        List<Programare> toateProgramarile = ProgramareService.getInstance().readAll();

        String sql = "SELECT * FROM Facturi";

        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idFactura = rs.getInt("id_factura");
                double pret = rs.getDouble("pret");
                java.sql.Date sqlDate = rs.getDate("data_emiterii");
                LocalDate dataEmiterii = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                int idProgramare = rs.getInt("id_programare");
                String status = rs.getString("status_plata");

                Programare programareAsociata = toateProgramarile.stream()
                        .filter(p -> p.getIdProgramare() == idProgramare)
                        .findFirst()
                        .orElse(null);

                Factura f = new Factura(idFactura, pret, dataEmiterii, programareAsociata, status);
                facturi.add(f);
            }
        } catch (SQLException e) {
            System.err.println("(FacturaService) Eroare la citirea facturilor.");
            e.printStackTrace();
        }
        return facturi;
    }

    @Override
    public void update(Factura entitate) {
        String sql = "UPDATE Facturi SET status_plata = ? WHERE id_factura = ?";
        try (Connection conn = DatabaseManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entitate.getStatusPlata());
            pstmt.setInt(2, entitate.getIdFactura());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("(FacturaService) Eroare la actualizarea facturii.");
            e.printStackTrace();
        }
    }

    @Override
    public Factura read(int id) {
        return null;
    }

    @Override
    public void delete(int id) {
    }
}