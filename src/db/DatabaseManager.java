package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//clasa Singleton - rolul ei este sa se conecteze la Oracle si sa imi dea conexiunea deschisa ori de cate ori am nevoie de ea in operatiile din servicii
public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;

    private final String URL = "jdbc:oracle:thin:@localhost:1521/homedb1";
    private final String USER = "system";
    private final String PASSWORD = "parolabd";

    //constructor privat pt singleton
    private DatabaseManager() {
//        try {
//            this.connection= DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("(DatabaseManager) Conexiune stabilita cu succes la Oracle");
//        } catch (SQLException e) {
//            System.err.println("(DatabaseManager) Eroare la conectarea cu baza de date.");
//            System.out.println(e.getMessage());
//        }
    }

    //metoda prin care clasele cer instanta singleton-ului
    public static DatabaseManager getInstance() {
        if(instance==null) {
            instance=new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("(DatabaseManager) Eroare la recrearea conexiunii cu baza de date");
            e.printStackTrace();
        }
        return connection;
    }
}
