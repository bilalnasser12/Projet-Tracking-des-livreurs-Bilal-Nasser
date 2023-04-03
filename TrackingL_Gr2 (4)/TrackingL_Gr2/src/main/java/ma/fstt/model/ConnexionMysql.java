package ma.fstt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionMysql {
    public Connection cn=null;
    public static Connection connectionDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/glovo" ,"root" ,"" );
            System.out.println("connexion reussite");
            return cn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("connexion echouee");
            e.printStackTrace();
            return null;
        }
    }
}
