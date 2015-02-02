package pt.uminho.lei.dss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {
    
    private static String DB_TYPE = "mysql";
    private static String HOST = "servidor.freeum.info";
    private static String PORT = "5223";
    private static String USER = "habitat";
    private static String PASSWORD = "password";
    private static String DATABASE = "habitat";
    
    public static String getURL() {
        return "jdbc:" + DB_TYPE + "://" + HOST + ":" + PORT + "/" + DATABASE;
    }
    
    public static String getUser() {
        return USER;
    }
    
    public static String getPassword() {
        return PASSWORD;
    }
    
    public static Connection openConnection() throws SQLException{
        return DriverManager.getConnection(getURL(), getUser(), getPassword());
    }
    
    public static void closeConnection(Connection c) throws SQLException{
        c.close();
    }
    
    
}
