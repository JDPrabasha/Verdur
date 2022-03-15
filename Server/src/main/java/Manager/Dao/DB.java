package Manager.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
//    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
//    private static String dbUrl = "jdbc:mysql://localhost:3306/";
//    private static String dbName = "verdur_db";
//    private static String dbUname = "root";
//    private static String dbPass = "";

    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://verdur.mysql.database.azure.com/";
    private static String dbName = "verdur";
    private static String dbUname = "dulaj@verdur";
    private static String dbPass = "hojwe1-Tynxod-razveh";


    public static Connection initializeDB() throws ClassNotFoundException, SQLException {
//        String dbDriver = "com.mysql.jdbc.Driver";

        Class.forName(dbDriver);
//        Connection con = DriverManager.getConnection(dbUrl+dbName+"?useSSL=true&autoReconnect=true",dbUname,dbPass);
        Connection con = DriverManager.getConnection(dbUrl+dbName+"?useSSL=true&autoReconnect=true",dbUname,dbPass);
        return con;
    }
}
