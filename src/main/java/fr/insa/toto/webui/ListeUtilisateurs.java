package fr.insa.toto.webui;

import java.security.Principal;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import fr.insa.beuvron.utils.database.ConnectionPool;
import fr.insa.beuvron.vaadin.utils.dataGrid.ResultSetGrid;

@Route(value = "liste-u")
@PageTitle ("Liste des utilisateurs")
public class ListeUtilisateurs extends VerticalLayout {

    public ListeUtilisateurs() {
        this.add(new H1("Liste des utilisateurs"));
        this.add(new RouterLink("Retour au menu principal", Principale.class));
        try(var con = ConnectionPool.getConnection();
            var pst = con.prepareStatement("SELECT * FROM utilisateur")) {
            this.add(new ResultSetGrid(pst));
        } catch (Exception e) {
            this.add(new H1("Erreur SQL : " + e.getMessage()));
        }

    }
    
}
