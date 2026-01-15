package entities;

import java.time.LocalDate;

public class Vente {

    private int id;
    private int idMedicament;
    private LocalDate dateVente;
    private int quantite;

    public Vente() {
    }

    public Vente(int id, int idMedicament, LocalDate dateVente, int quantite) {
        this.id = id;
        this.idMedicament = idMedicament;
        this.dateVente = dateVente;
        this.quantite = quantite;
    }

    public Vente(int idMedicament, LocalDate dateVente, int quantite) {
        this.idMedicament = idMedicament;
        this.dateVente = dateVente;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public LocalDate getDateVente() {
        return dateVente;
    }

    public void setDateVente(LocalDate dateVente) {
        this.dateVente = dateVente;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", idMedicament=" + idMedicament +
                ", dateVente=" + dateVente +
                ", quantite=" + quantite +
                '}';
    }
}
