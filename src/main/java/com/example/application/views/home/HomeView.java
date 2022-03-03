package com.example.application.views.home;

import ch.qos.logback.core.Layout;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@PageTitle("Home")
@Route(value = "Home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        vLayout.setHeightFull();

        HorizontalLayout layout = new HorizontalLayout();
        layout.getStyle().set("background-color", "rgba(238,238,238,0.75");
        layout.setWidthFull();
        layout.setHeight("300px");
        layout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

        this.getElement().getStyle().set("background-image", "url('uniloy-large.png')");
        setPadding(false);

        Image img = new Image("DATAHONE-logo.png",
                "Error loading image, image has been removed from source (best guess)");
        img.setWidth("200px");

        Image logoImg = new Image("logo.png",
                "Error loading image, image has been removed from source (best guess)");
        logoImg.setWidth("500px");

        H2 text = new H2("Welcome to Logoplaste Intelligent Factory");

        vLayout.add(logoImg, text, img);
        layout.add(vLayout);
        add(layout);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
