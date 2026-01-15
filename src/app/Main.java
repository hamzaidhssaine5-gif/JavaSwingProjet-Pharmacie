package app;



import entities.Fournisseur;
import service.MedicamentService;
import service.VenteService;
import entities.Medicament;
import entities.Vente;
import java.time.LocalDate;
import service.FournisseurService;

public class Main {

    public static void main(String[] args) {

        FournisseurService fournisseurService = new FournisseurService();
        MedicamentService medicamentService = new MedicamentService();
        VenteService venteService = new VenteService();

        System.out.println("===== TEST FOURNISSEUR =====");
        Fournisseur f = new Fournisseur("PharmaPlus", "Marrakech", "0612345678");
        fournisseurService.create(f);
        System.out.println("Created: " + f);

        System.out.println("\n===== TEST MEDICAMENT =====");
        Medicament m = new Medicament(
                "Paracetamol",
                "Antalgique",
                12.5,
                50,
                f.getId()
        );
        medicamentService.create(m);
        System.out.println("Created: " + m);

        System.out.println("\n===== STOCK BEFORE VENTE =====");
        System.out.println(medicamentService.findById(m.getId()));

        System.out.println("\n===== TEST VENTE =====");
        Vente v = new Vente(
                m.getId(),
                LocalDate.now(),
                10
        );

        boolean venteOK = venteService.create(v);
        System.out.println("Vente created: " + venteOK);

        System.out.println("\n===== STOCK AFTER VENTE =====");
        System.out.println(medicamentService.findById(m.getId()));

        System.out.println("\n===== TEST VENTE WITH INSUFFICIENT STOCK =====");
        Vente v2 = new Vente(
                m.getId(),
                LocalDate.now(),
                1000
        );

        boolean venteFail = venteService.create(v2);
        System.out.println("Vente created: " + venteFail);

        System.out.println("\n===== FINAL STOCK =====");
        System.out.println(medicamentService.findById(m.getId()));
    }
}

