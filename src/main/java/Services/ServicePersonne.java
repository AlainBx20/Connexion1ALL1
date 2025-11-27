package Services;

import Entite.Personne;
import Utlis.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicePersonne implements IService<Personne> {
  private   Connection con2= DataSource.getInstance().getCon();

    @Override
    public boolean ajouter(Personne personne) throws SQLException {
        String sql = "INSERT INTO personne (nom, prenom, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con2.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, personne.getNom());
            ps.setString(2, personne.getPrenom());
            ps.setInt(3, personne.getAge());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        personne.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean supprimer(Personne personne) throws SQLException {
        String sql = "DELETE FROM personne WHERE id = ?";
        try (PreparedStatement ps = con2.prepareStatement(sql)) {
            ps.setInt(1, personne.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public boolean modifier(Personne personne) throws SQLException {
        String sql = "UPDATE personne SET nom = ?, prenom = ?, age = ? WHERE id = ?";
        try (PreparedStatement ps = con2.prepareStatement(sql)) {
            ps.setString(1, personne.getNom());
            ps.setString(2, personne.getPrenom());
            ps.setInt(3, personne.getAge());
            ps.setInt(4, personne.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public List<Personne> afficher() throws SQLException {
        String sql = "SELECT id, nom, prenom, age FROM personne";
        List<Personne> list = new ArrayList<>();
        try (PreparedStatement ps = con2.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                int age = rs.getInt("age");
                Personne p = new Personne(id, nom, prenom, age);
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public Personne findById(int id) throws SQLException {
        String sql = "SELECT id, nom, prenom, age FROM personne WHERE id = ?";
        try (PreparedStatement ps = con2.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Personne(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getInt("age")
                    );
                }
            }
        }
        return null;
    }
}
