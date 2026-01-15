package service;

import entities.Medicament;
import connection.DBConnection;
import dao.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MedicamentService implements IDao<Medicament> {

    @Override
    public boolean create(Medicament m) {
        String sql = "INSERT INTO medicament (nom, famille, prix, stock, id_fournisseur) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getNom());
            ps.setString(2, m.getFamille());
            ps.setDouble(3, m.getPrix());
            ps.setInt(4, m.getStock());

            if (m.getIdFournisseur() != null) {
                ps.setInt(5, m.getIdFournisseur());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setId(rs.getInt(1));
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Medicament m) {
        String sql = "UPDATE medicament SET nom=?, famille=?, prix=?, stock=?, id_fournisseur=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNom());
            ps.setString(2, m.getFamille());
            ps.setDouble(3, m.getPrix());
            ps.setInt(4, m.getStock());

            if (m.getIdFournisseur() != null) {
                ps.setInt(5, m.getIdFournisseur());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setInt(6, m.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Medicament m) {
        String sql = "DELETE FROM medicament WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Medicament findById(int id) {
        String sql = "SELECT * FROM medicament WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Medicament m = new Medicament();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setFamille(rs.getString("famille"));
                m.setPrix(rs.getDouble("prix"));
                m.setStock(rs.getInt("stock"));

                int idF = rs.getInt("id_fournisseur");
                m.setIdFournisseur(rs.wasNull() ? null : idF);

                return m;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Medicament> findAll() {
        List<Medicament> liste = new ArrayList<>();
        String sql = "SELECT * FROM medicament";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicament m = new Medicament();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setFamille(rs.getString("famille"));
                m.setPrix(rs.getDouble("prix"));
                m.setStock(rs.getInt("stock"));

                int idF = rs.getInt("id_fournisseur");
                m.setIdFournisseur(rs.wasNull() ? null : idF);

                liste.add(m);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    // 🔹 Extra methods (NOT part of IDao)

    


    public List<Medicament> findStockBas(int seuil) {
        List<Medicament> liste = new ArrayList<>();
        String sql = "SELECT * FROM medicament WHERE stock < ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, seuil);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Medicament m = new Medicament();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setFamille(rs.getString("famille"));
                m.setPrix(rs.getDouble("prix"));
                m.setStock(rs.getInt("stock"));

                int idF = rs.getInt("id_fournisseur");
                m.setIdFournisseur(rs.wasNull() ? null : idF);

                liste.add(m);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;
    }
}

