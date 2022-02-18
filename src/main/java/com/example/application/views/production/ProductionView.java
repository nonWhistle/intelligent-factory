package com.example.application.views.production;

import com.example.application.repositories.CounterRepository;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Production")
@Route(value = "Production", layout = MainLayout.class)
public class ProductionView extends VerticalLayout
{
    private final CounterRepository counterRepository;
    private VerticalLayout layout = new VerticalLayout();
    private final Grid<Counter> grid = new Grid<>();

    public ProductionView(CounterRepository counterRepository)
    {
        this.counterRepository = counterRepository;

        Label u1Label = new Label("Uniloy One");
        u1Label.getStyle().set("font-size", "large");

        layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        layout.getStyle().set("background", "rgb(211, 211, 211)");
        layout.add(u1Label, grid);

        add(layout);
        createGrid();
        createU1Chart();
    }

    private void updateGrid()
    {
        grid.setItems(counterRepository.findAll());
    }

    private void createGrid()
    {
        grid.setWidthFull();
        grid.addColumn(Counter::getMachine_output).setHeader("Machine Output").setKey("1");
        grid.addColumn(Counter::getTrimmer_input).setHeader("Trimmer Input");
        grid.addColumn(Counter::getTrimmer_output).setHeader("Trimmer Output");
        grid.addColumn(Counter::getLeaktester_input).setHeader("Leaktester Input");
        grid.addColumn(Counter::getLeaktester_output).setHeader("Leaktester Output");
        grid.setMaxHeight(100, Unit.PIXELS);
        updateGrid();
    }

    private void createU1Chart()
    {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration configuration = chart.getConfiguration();

        XAxis xAxis = configuration.getxAxis();
        xAxis.setCategories(" ", " ", " ", " ", " ");

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle("Number of bottles");

        configuration.addSeries(new ListSeries("Machine output",
                counterRepository.getMachineOut()));
        configuration.addSeries(new ListSeries("Trimmer input",
                counterRepository.getTrimmerIn()));
        configuration.addSeries(new ListSeries("Trimmer output",
                counterRepository.getTrimmerOut()));
        configuration.addSeries(new ListSeries("Leaktester input",
                counterRepository.getLeaktesterIn()));
        configuration.addSeries(new ListSeries("Leaktester output",
                counterRepository.getLeaktesterOut()));

        layout.add(chart);
    }
}
