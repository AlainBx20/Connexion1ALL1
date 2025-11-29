import Controller.PersonneController;
import Entite.Personne;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonneController controller = new PersonneController();

        // 1. CREATE - Add a new Personne
        System.out.println("=== CREATE OPERATION ===");
        Personne p1 = new Personne("Ben Fadhel", "Sinda", 25);
        if (controller.addPersonne(p1)) {
            System.out.println("✓ Personne added successfully: " + p1);
        } else {
            System.out.println("✗ Failed to add personne");
        }

        // Add another person for testing
        Personne p2 = new Personne("Dupont", "Jean", 30);
        if (controller.addPersonne(p2)) {
            System.out.println("✓ Second personne added: " + p2);
        }

        // 2. READ - Get all Personnes
        System.out.println("\n=== READ ALL OPERATION ===");
        List<Personne> allPersonnes = controller.getAllPersonnes();
        if (allPersonnes != null && !allPersonnes.isEmpty()) {
            System.out.println("✓ All personnes retrieved:");
            for (Personne p : allPersonnes) {
                System.out.println("  " + p);
            }
        } else {
            System.out.println("✗ No personnes found");
        }

        // 3. READ BY ID - Get a specific Personne
        System.out.println("\n=== READ BY ID OPERATION ===");
        if (!allPersonnes.isEmpty()) {
            int firstId = allPersonnes.get(0).getId();
            Personne foundPersonne = controller.getPersonneById(firstId);
            if (foundPersonne != null) {
                System.out.println("✓ Personne found by ID " + firstId + ": " + foundPersonne);
            } else {
                System.out.println("✗ Personne not found");
            }
        }

        // 4. UPDATE - Modify a Personne
        System.out.println("\n=== UPDATE OPERATION ===");
        if (!allPersonnes.isEmpty()) {
            Personne personneToUpdate = allPersonnes.get(0);
            personneToUpdate.setNom("Ben Fadhel Updated");
            personneToUpdate.setAge(26);
            if (controller.updatePersonne(personneToUpdate)) {
                System.out.println("✓ Personne updated successfully: " + personneToUpdate);
            } else {
                System.out.println("✗ Failed to update personne");
            }

            // Verify update
            Personne verifyUpdate = controller.getPersonneById(personneToUpdate.getId());
            if (verifyUpdate != null) {
                System.out.println("✓ Verification - Updated personne: " + verifyUpdate);
            }
        }

        // 5. DELETE - Remove a Personne
        System.out.println("\n=== DELETE OPERATION ===");
        allPersonnes = controller.getAllPersonnes();
        if (allPersonnes != null && allPersonnes.size() > 1) {
            Personne personneToDelete = allPersonnes.get(allPersonnes.size() - 1);
            if (controller.deletePersonne(personneToDelete)) {
                System.out.println("✓ Personne deleted successfully: " + personneToDelete);
            } else {
                System.out.println("✗ Failed to delete personne");
            }

            // Verify deletion
            List<Personne> remaining = controller.getAllPersonnes();
            System.out.println("✓ Remaining personnes after deletion:");
            if (remaining != null) {
                for (Personne p : remaining) {
                    System.out.println("  " + p);
                }
            }
        }

        System.out.println("\n=== ALL OPERATIONS COMPLETED ===");
    }
}
