package service;

import entities.Vente;
import connection.DBConnection;
import dao.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
public class VenteService implements IDao<Vente> {

    @Override
    public boolean create(Vente v) {
        String insertVente = 
            "INSERT INTO vente (id_medicament, date_vente, quantite) VALUES (?, ?, ?)";
        String updateStock = 
            "UPDATE medicament SET stock = stock - ? WHERE id = ? AND stock >= ?";

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); //  START TRANSACTION

            // Update stock
            try (PreparedStatement psStock = conn.prepareStatement(updateStock)) {
                psStock.setInt(1, v.getQuantite());
                psStock.setInt(2, v.getIdMedicament());
                psStock.setInt(3, v.getQuantite());

                int rows = psStock.executeUpdate();
                if (rows == 0) {
                    conn.rollback();
                    System.out.println("Stock insuffisant !");
                    return false;
                }
            }

            // Insert vente
            try (PreparedStatement psVente =
                         conn.prepareStatement(insertVente, Statement.RETURN_GENERATED_KEYS)) {

                psVente.setInt(1, v.getIdMedicament());
                psVente.setDate(2, java.sql.Date.valueOf(v.getDateVente()));
                psVente.setInt(3, v.getQuantite());
                psVente.executeUpdate();

                ResultSet rs = psVente.getGeneratedKeys();
                if (rs.next()) {
                    v.setId(rs.getInt(1));
                }
            }

            conn.commit(); //  COMMIT
            return true;

        } catch (SQLException ex) {
            try {
                if (conn != null) conn.rollback(); //  ROLLBACK
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            return false;

        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean update(Vente v) {
        String sql = "UPDATE vente SET id_medicament=?, date_vente=?, quantite=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getIdMedicament());
            ps.setDate(2, Date.valueOf(v.getDateVente()));
            ps.setInt(3, v.getQuantite());
            ps.setInt(4, v.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Vente v) {
        String sql = "DELETE FROM vente WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Vente findById(int id) {
        String sql = "SELECT * FROM vente WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vente v = new Vente();
                v.setId(rs.getInt("id"));
                v.setIdMedicament(rs.getInt("id_medicament"));
                v.setDateVente(rs.getDate("date_vente").toLocalDate());
                v.setQuantite(rs.getInt("quantite"));
                return v;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vente> findAll() {
        List<Vente> liste = new ArrayList<>();
        String sql = "SELECT * FROM vente";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vente v = new Vente();
                v.setId(rs.getInt("id"));
                v.setIdMedicament(rs.getInt("id_medicament"));
                v.setDateVente(rs.getDate("date_vente").toLocalDate());
                v.setQuantite(rs.getInt("quantite"));
                liste.add(v);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    public Map<String, Integer> ventesParFamille() {
        Map<String, Integer> data = new LinkedHashMap<>();
        String sql = "SELECT m.famille, SUM(v.quantite) AS total FROM vente v JOIN medicament m ON m.id = v.id_medicament GROUP BY m.famille ORDER BY total DESC";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.put(rs.getString("famille"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
