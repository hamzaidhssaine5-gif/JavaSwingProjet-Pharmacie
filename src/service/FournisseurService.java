/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Fournisseur;
import connection.DBConnection;
import dao.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

public class FournisseurService implements IDao<Fournisseur> {

    @Override
    public boolean create(Fournisseur f) {
        String sql = "INSERT INTO fournisseur (nom, ville, contact) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, f.getNom());
            ps.setString(2, f.getVille());
            ps.setString(3, f.getContact());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                f.setId(rs.getInt(1));
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Fournisseur f) {
        String sql = "UPDATE fournisseur SET nom = ?, ville = ?, contact = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, f.getNom());
            ps.setString(2, f.getVille());
            ps.setString(3, f.getContact());
            ps.setInt(4, f.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Fournisseur f) {
        String sql = "DELETE FROM fournisseur WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, f.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Fournisseur findById(int id) {
        String sql = "SELECT * FROM fournisseur WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Fournisseur f = new Fournisseur();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setVille(rs.getString("ville"));
                f.setContact(rs.getString("contact"));
                return f;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Fournisseur> findAll() {
        List<Fournisseur> liste = new ArrayList<>();
        String sql = "SELECT * FROM fournisseur";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Fournisseur f = new Fournisseur();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setVille(rs.getString("ville"));
                f.setContact(rs.getString("contact"));
                liste.add(f);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;
    }

    // 🔹 Extra methods (business logic)

    public List<Fournisseur> findByVille(String ville) {
        List<Fournisseur> liste = new ArrayList<>();
        String sql = "SELECT * FROM fournisseur WHERE ville = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ville);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Fournisseur f = new Fournisseur();
                f.setId(rs.getInt("id"));
                f.setNom(rs.getString("nom"));
                f.setVille(rs.getString("ville"));
                f.setContact(rs.getString("contact"));
                liste.add(f);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;
    }
}

