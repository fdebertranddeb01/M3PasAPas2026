package fr.insa.toto.webui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

// @Route(value = "", layout = MainLayout.class)
@Route(value = "")
@PageTitle("Pas à Pas M3 2026")
public class Principale extends VerticalLayout {

    public Principale() {
        // This is the main view of the application
        this.add(new H1("Welcome to Pas à Pas M3 2026!"));
        this.add(new RouterLink("inscription", Inscription.class));
        this.add(new RouterLink("liste des utilisateurs", ListeUtilisateurs.class));
    }

}
