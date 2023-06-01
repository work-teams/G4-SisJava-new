package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection con;

    public Connection getConnection() {
        try {
            //String myBD = "jdbc:mysql://3.tcp.ngrok.io:23947/sis_java";
            String myBD = "jdbc:mysql://localhost:3306/sis_java?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            //con = DriverManager.getConnection(myBD, "root", "230499.");
            con = DriverManager.getConnection(myBD, "root", "12345678");
            
            //String myBD = "jdbc:mysql://localhost:3306/sis_java";
            //con = DriverManager.getConnection(myBD, "root", "");
            //String myBD = "jdbc:mysql://localhost:3306/gpsw";
            //con = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10484637","sql10484637","YrRRH2Lzpl");
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
