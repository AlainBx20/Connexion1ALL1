package Controller;

import Entite.Personne;
import Services.ServicePersonne;

import java.sql.SQLException;
import java.util.List;

public class PersonneController {
    private ServicePersonne servicePersonne;

    public PersonneController() {
        this.servicePersonne = new ServicePersonne();
    }

    public boolean addPersonne(Personne personne) {
        try {
            return servicePersonne.ajouter(personne);
        } catch (SQLException e) {
            System.out.println("Error adding personne: " + e.getMessage());
            return false;
        }
    }

    public List<Personne> getAllPersonnes() {
        try {
            return servicePersonne.afficher();
        } catch (SQLException e) {
            System.out.println("Error retrieving personnes: " + e.getMessage());
            return null;
        }
    }

    public Personne getPersonneById(int id) {
        try {
            return servicePersonne.findById(id);
        } catch (SQLException e) {
            System.out.println("Error finding personne: " + e.getMessage());
            return null;
        }
    }

    public boolean updatePersonne(Personne personne) {
        try {
            return servicePersonne.modifier(personne);
        } catch (SQLException e) {
            System.out.println("Error updating personne: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePersonne(Personne personne) {
        try {
            return servicePersonne.supprimer(personne);
        } catch (SQLException e) {
            System.out.println("Error deleting personne: " + e.getMessage());
            return false;
        }
    }
}
