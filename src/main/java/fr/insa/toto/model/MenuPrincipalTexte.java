package fr.insa.toto.model;

import java.sql.Connection;
import java.sql.SQLException;

import fr.insa.beuvron.utils.ConsoleFdB;
import fr.insa.beuvron.utils.database.ConnectionSimpleSGBD;

public class MenuPrincipalTexte {

    public static void menuPrincipal(Connection con) {
        int rep = -1;
        while (rep != 0) {
            System.out.println("Menu Principal");
            int i = 1;
            System.out.println((i++) + ") Gestion du Schéma");
            System.out.println((i++) + ") Gestion des Utilisateurs");
            System.out.println("0. Quitter");
            rep = ConsoleFdB.entreeInt("votre choix (0 pour quitter) : ");
            int j = 1;
            if (rep == j++) {
                GestionSchema.menuGestionSchema(con);
            } else if (rep == j++) {
                GestionUtilisateursDirect.menuGestionUtilisateurs(con);
            } else if (rep != 0) {
                System.out.println("Choix invalide");
            }
        }
    }

    public static void main(String[] args) {
        try (
                Connection con = ConnectionSimpleSGBD.mysqlServeurPourM3();) {
            menuPrincipal(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
