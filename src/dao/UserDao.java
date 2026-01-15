/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author dell
 */
import connection.DBConnection;
import java.sql.*;

public class UserDao {

    public boolean checkLogin(String username, String password) {
        String sql = "SELECT 1 FROM users WHERE username=? AND password_hash=?"; // if plain, rename column

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password); // plain password (simple)

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

