package entities;

public class Medicament {

    private int id;
    private String nom;
    private String famille;
    private double prix;
    private int stock;
    private Integer idFournisseur; 

    public Medicament() {
    }

    public Medicament(int id, String nom, String famille, double prix, int stock, Integer idFournisseur) {
        this.id = id;
        this.nom = nom;
        this.famille = famille;
        this.prix = prix;
        this.stock = stock;
        this.idFournisseur = idFournisseur;
    }

    public Medicament(String nom, String famille, double prix, int stock, Integer idFournisseur) {
        this.nom = nom;
        this.famille = famille;
        this.prix = prix;
        this.stock = stock;
        this.idFournisseur = idFournisseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Integer idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @Override
    public String toString() {
        return nom;  // ou getNom(), selon ta classe
    }
}
