package fr.insa.toto.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.insa.beuvron.utils.ConsoleFdB;

public class GestionUtilisateursDirect {

    public static void creeUtilisateur(Connection con,
         String nom, String pass) throws SQLException {
        try (var pst = con.prepareStatement(
            "INSERT INTO utilisateur (surnom, pass) VALUES (?, ?)")) {
            pst.setString(1, nom);
            pst.setString(2, pass);
            pst.executeUpdate();
        }
    }

    public static void supprimeUtilisateur(Connection con, 
        int id) throws SQLException {
        try (var pst = con.prepareStatement(
            "DELETE FROM utilisateur WHERE id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
     }

    public static void demandeCreationUtilisateur(Connection con) throws SQLException {
        String nom = ConsoleFdB.entreeString("nom de l'utilisateur : ");
        String pass = ConsoleFdB.entreeString("mot de passe : ");
        creeUtilisateur(con, nom, pass);
    }

    public static void demandeSuppressionUtilisateur(Connection con) throws SQLException {
        int id = ConsoleFdB.entreeInt("id de l'utilisateur à supprimer : ");
        supprimeUtilisateur(con, id);
    }

    public static void listeTousUtilisateurs(Connection con) 
    throws SQLException {
        try (var pst = con.prepareStatement("SELECT id, surnom, pass FROM utilisateur");
             ResultSet rs = pst.executeQuery()) {
            System.out.println("Liste des utilisateurs :");
            while (rs.next() ) {
                int id = rs.getInt("id");
                String nom = rs.getString(2);
                String pass = rs.getString("pass");
                System.out.println("id : " + id + ", nom : " + nom + ", pass : " + pass);
            }
        }
    }

    public static void menuGestionUtilisateurs(Connection con) {
        int rep = -1;
        while (rep != 0) {
            System.out.println("Menu Gestion des utilisateurs");
            int i = 1;
            System.out.println((i++) + ") lister tous les utilisateurs");
            System.out.println((i++) + ") Créer un nouvel utilisateur");
            System.out.println((i++) + ") Supprimer un utilisateur");
            System.out.println("0. Quitter");
            rep = ConsoleFdB.entreeInt("votre choix (0 pour quitter) : ");
            try {
                int j = 1;
                if (rep == j++) {
                    listeTousUtilisateurs(con);
                } else if (rep == j++) {
                    demandeCreationUtilisateur(con);
                } else if (rep == j++) {
                    demandeSuppressionUtilisateur(con);
                } else if (rep != 0) {
                    System.out.println("Choix invalide");
                }
            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
            }
        }

    }
}
