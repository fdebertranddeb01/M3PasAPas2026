package fr.insa.toto.webui;

import java.sql.SQLException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import fr.insa.beuvron.utils.database.ConnectionPool;
import fr.insa.toto.model.GestionUtilisateursDirect;

@Route(value = "inscription")
@PageTitle("Inscription")
public class Inscription extends VerticalLayout {

    private TextField nomField;
    private PasswordField passField;
    private VerticalLayout messageLayout;

    public Inscription() {
        this.add(new H1("Inscription"));
        this.add(new RouterLink("Retour au menu principal", Principale.class));
        this.nomField = new TextField("Nom d'utilisateur");
        this.passField = new PasswordField("Mot de passe");
        Button submitButton = new Button("S'inscrire", event -> {
            String nom = nomField.getValue();
            String pass = passField.getValue();
            try (var con = ConnectionPool.getConnection()) {
                GestionUtilisateursDirect.creeUtilisateur(con, nom, pass);
                afficherMessage("Utilisateur inscrit : " + nom);
            } catch (SQLException e) {
                e.printStackTrace();
                afficherMessage("Erreur SQL lors de l'inscription : " + e.getMessage());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                afficherMessage("Erreur lors de l'inscription : " + e.getMessage());
                return;
            }
        });
        this.messageLayout = new VerticalLayout();
        this.add(nomField, passField, submitButton, messageLayout);
    }

    public void afficherMessage(String message) {
        messageLayout.removeAll();
        messageLayout.add(new H1(message));
    }

}
