package fr.insa.toto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.insa.beuvron.utils.ConsoleFdB;
import fr.insa.beuvron.utils.database.ConnectionSimpleSGBD;

public class GestionSchema {

    public static void creationSchema(Connection con) throws SQLException {
        String tableUtilisateur = "CREATE TABLE utilisateur ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nom VARCHAR(50) NOT NULL UNIQUE,"
                + "pass VARCHAR(30) NOT NULL"
                + ")";
        try (PreparedStatement pst = con.prepareStatement(tableUtilisateur)) {
            pst.executeUpdate();
        }
}

    public static void suppressionSchema(Connection con) {
        try(var pst = con.prepareStatement("DROP TABLE utilisateur")) {
            pst.executeUpdate();
        } catch (SQLException e) {
            // Si la table n'existe pas, on ignore l'erreur
         }
     }

    public static void menuGestionSchema(Connection con) {
        int rep = -1;
        while (rep != 0) {
            System.out.println("Menu Gestion du schéma de la base de données");
            int i = 1;
            System.out.println((i++) + ") Créer le schéma");
            System.out.println((i++) + ") supprimer tout");
            System.out.println("0. Quitter");
            rep = ConsoleFdB.entreeInt("votre choix (0 pour quitter) : ");
            try {
                int j = 1;
                if (rep == j++) {
                    creationSchema(con);
                } else if (rep == j++) {
                    suppressionSchema(con);
                } else if (rep != 0) {
                    System.out.println("Choix invalide");
                }
            } catch (SQLException e) {
                System.out.println("Erreur SQL : " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try (
                Connection con = ConnectionSimpleSGBD.mysqlServeurPourM3();) {
            menuGestionSchema(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
