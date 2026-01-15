/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/pharmacie?serverTimezone=UTC";
    private static final String USER = "root";   // adapter
    private static final String PASSWORD = "";   // adapter

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
