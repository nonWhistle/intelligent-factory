package com.example.application.views.production;

import com.example.application.displayData.Alarm;
import com.example.application.displayData.CountData;
import com.example.application.displayData.DowntimeData;
import com.example.application.displayData.TrendData;
import com.example.application.repositories.CounterRepository;
import com.example.application.repositories.DowntimeRepo;
import com.example.application.repositories.TrendRepo;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Production")
@Route(value = "Production", layout = MainLayout.class)
public class ProductionView extends VerticalLayout
{
    private final CounterRepository counterRepository;
    private final TrendRepo trendRepo;
    private final DowntimeRepo downtimeRepo;
    private final VerticalLayout verticalLayout = new VerticalLayout();
    private final CountData countData;
    private final TrendData trendData;
    private final DowntimeData downtimeData;
    private final Alarm alarm;

    public ProductionView(CounterRepository counterRepository, TrendRepo trendRepo, DowntimeRepo downtimeRepo)
    {
        this.counterRepository = counterRepository;
        countData = new CountData(counterRepository);

        this.trendRepo = trendRepo;
        trendData = new TrendData(this.trendRepo);

        this.downtimeRepo = downtimeRepo;
        downtimeData = new DowntimeData();

        alarm = new Alarm();
        createMenuBar();

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(verticalLayout);
    }

    private void createMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        /*
        Uniloys
         */
        MenuItem countsMenuItem = menuBar.addItem("Counts");
        SubMenu countsSubMenu = countsMenuItem.getSubMenu();
        MenuItem countUniloys = countsSubMenu.addItem("Uniloys");
        SubMenu countUniloy = countUniloys.getSubMenu();

        //Uniloy 1
        countUniloy.addItem("Uniloy 1", menuItemClickEvent -> {
            drawGraph("Uniloy 1");
        });

        //Uniloy 2
        countUniloy.addItem("Uniloy 2", menuItemClickEvent -> {
            drawGraph("Uniloy 2");
        });

        //Uniloy 3
        countUniloy.addItem("Uniloy 3", menuItemClickEvent -> {
            drawGraph("Uniloy 3");
        });

        /*
        Trends
         */
        MenuItem trendsMenuItem = menuBar.addItem("Trends");
        SubMenu trendsSubMenu = trendsMenuItem.getSubMenu();
        MenuItem trendsUniloys = trendsSubMenu.addItem("Uniloys");
        SubMenu trendsUniloy = trendsUniloys.getSubMenu();
        trendsUniloy.addItem("Uniloy 1", menuItemClickEvent -> {
            drawTrendGraph("Uniloy 1");
        });

        trendsUniloy.addItem("Uniloy 2", menuItemClickEvent -> {
            drawTrendGraph("Uniloy 2");
        });

        /*
        Downtime
         */
        MenuItem downtimeMenuItem = menuBar.addItem("Downtime");
        SubMenu downtimeSubMenu = downtimeMenuItem.getSubMenu();
        MenuItem downtimeUniloys = downtimeSubMenu.addItem("Uniloys");
        SubMenu downtimeUniloy1 = downtimeUniloys.getSubMenu();
        downtimeUniloy1.addItem("Uniloy 1", menuItemClickEvent -> {
            verticalLayout.removeAll();
            verticalLayout.setSpacing(false);
            verticalLayout.setPadding(false);
            verticalLayout.add(new H2("Uniloy 1 Runtime"));
            verticalLayout.add(drawGrid());
        });


        /*
        Cycletimes
         */
        MenuItem cycle_timesMenuItem = menuBar.addItem("Cycle Times");
        SubMenu cycle_timesSubMenu = cycle_timesMenuItem.getSubMenu();
        MenuItem cycle_timesUniloys = cycle_timesSubMenu.addItem("Uniloys");
        SubMenu cycle_timesUniloy1 = cycle_timesUniloys.getSubMenu();
        cycle_timesUniloy1.addItem("Uniloy 1", menuItemClickEvent -> {
            //trendData.createData(2020, 02, 22);
            //downtimeData.createDowntimeData();
        });

        add(menuBar);
    }

    public void drawGraph(String machine)
    {
        verticalLayout.removeAll();
        verticalLayout.add(countData.createChart(machine));
        if(countData.checkEfficiency(machine)) {
            verticalLayout.add(alarm.createAlarmLabel(machine,
                    counterRepository.getCurrentMachineOut(),
                    counterRepository.getCurrentLeaktesterOut()));

            Button alert = new Button("Details");
            alert.addThemeVariants(ButtonVariant.LUMO_ERROR);
            switch (machine) {
                case "Uniloy 1":
                    alert.addClickListener(buttonClickEvent -> alarm.createDialog(counterRepository.getCurrentTrimmerIn(),
                            counterRepository.getCurrentTrimmerOut(), counterRepository.getCurrentLeaktesterIn(),
                            counterRepository.getCurrentLeaktesterOut()));
                    break;
                case "Uniloy 2":
                    alert.addClickListener(buttonClickEvent -> alarm.createDialog(counterRepository.getU2CurrentTrimmerIn(),
                            counterRepository.getU2CurrentTrimmerOut(), counterRepository.getU2CurrentLeaktesterIn(),
                            counterRepository.getU2CurrentLeaktesterOut()));
                    break;
                case "Uniloy 3":
                    alert.addClickListener(buttonClickEvent -> alarm.createDialog(counterRepository.getU3CurrentTrimmerIn(),
                            counterRepository.getU3CurrentTrimmerOut(), counterRepository.getU3CurrentLeaktesterIn(),
                            counterRepository.getU3CurrentLeaktesterOut()));
                    break;
                default:
                    System.out.println("Error");
            }
            verticalLayout.add(alert);
        }
    }

    public void drawTrendGraph(String machine)
    {
        verticalLayout.removeAll();
        switch(machine) {
            case "Uniloy 1":
                verticalLayout.add(trendData.createTrendChart(machine,
                        trendRepo.getU1Total(),
                        trendRepo.getU1Dates()));
                break;
            case "Uniloy 2":
                verticalLayout.add(trendData.createTrendChart("Uniloy 2",
                        trendRepo.getU2Total(),
                        trendRepo.getU2Dates()));
                break;
            default:
                System.out.println("Error");
        }
    }

    public Grid drawGrid()
    {
        List<Machine> getall = downtimeRepo.getAll();
        Grid<Machine> grid = new Grid();
        HorizontalLayout layout = new HorizontalLayout();
        layout.setDefaultVerticalComponentAlignment(Alignment.START);
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setDefaultHorizontalComponentAlignment(Alignment.START);
        vLayout.setWidth("150px");
        Button saveBtn = new Button("Save");
        saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button deleteBtn = new Button("Delete");
        deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);

        ComboBox<String> comboBox = new ComboBox<>("Reason for downtime");
        comboBox.setClearButtonVisible(true);
        comboBox.setWidth("250px");
        add(comboBox);
        comboBox.setItems("Cleaning", "Servicing", "Awaiting parts", "No supply",
                "Maintenance", "No raw material", "Plant room fault", "Technician shortages");
        comboBox.setHelperText("Select a reason");

        grid.setWidthFull();
        grid.addColumn(Machine::getU1_date).setHeader("Date").setSortable(true);
        grid.addColumn(Machine::getU1_runTime).setHeader("Runtime");
        grid.addColumn(Machine::getDowntime).setHeader("Downtime");
        grid.addColumn(Machine::getU1_comments).setHeader("Comments");
        grid.setItemDetailsRenderer(
                new ComponentRenderer<>(downtime ->
                {
                    saveBtn.addClickListener(buttonClickEvent -> {
                        if(!comboBox.getValue().isEmpty()) {
                            downtime.setU1_comments(comboBox.getValue());
                            downtimeRepo.updateU1Comment(downtime);
                            redrawGrid();
                        }
                        comboBox.clear();
                    });
                    saveBtn.setWidth("100px");

                    deleteBtn.addClickListener(buttonClickEvent -> {
                        downtime.setU1_comments(null);
                        downtimeRepo.updateU1Comment(downtime);
                        redrawGrid();
                    });
                    deleteBtn.setWidth("100px");

                    return layout;
                }));

        vLayout.add(deleteBtn, saveBtn);
        layout.add(vLayout, comboBox);

        grid.setItems(getall);
        return grid;
    }

    public void redrawGrid()
    {
        verticalLayout.removeAll();
        verticalLayout.add(drawGrid());
    }
}
