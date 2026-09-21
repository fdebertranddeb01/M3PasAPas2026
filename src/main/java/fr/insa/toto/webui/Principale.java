package fr.insa.toto.webui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

// @Route(value = "", layout = MainLayout.class)
@Route(value = "")
@PageTitle("Pas à Pas M3 2026")
public class Principale extends VerticalLayout {

    public Principale() {
        // This is the main view of the application
        this.add(new H1("Welcome to Pas à Pas M3 2026!"));
    }
    
}
