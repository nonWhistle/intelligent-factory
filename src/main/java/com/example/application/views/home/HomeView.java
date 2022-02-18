package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "Home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(false);

        Image img = new Image("https://upload.wikimedia.org/wikipedia/en/d/d9/Logoplaste_logo.svg", "Hi!");
        img.setWidth("600px");
        add(img);

        add(new H2("Welcome to Logoplaste Intelligent Factory"));
        add(new Paragraph(""));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
