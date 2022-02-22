package com.example.application.views.production;

import com.example.application.displayData.Alarm;
import com.example.application.displayData.CountData;
import com.example.application.displayData.TrendData;
import com.example.application.repositories.CounterRepository;
import com.example.application.repositories.TrendRepo;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Production")
@Route(value = "Production", layout = MainLayout.class)
public class ProductionView extends VerticalLayout
{
    private final CounterRepository counterRepository;
    private final VerticalLayout verticalLayout = new VerticalLayout();
    private final CountData countData;
    private final TrendData trendData;
    private final TrendRepo trendRepo;
    private final Alarm alarm;

    public ProductionView(CounterRepository counterRepository, TrendRepo trendRepo)
    {
        this.counterRepository = counterRepository;
        countData = new CountData(counterRepository);
        this.trendRepo = trendRepo;
        trendData = new TrendData(this.trendRepo);
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
        SubMenu countUniloy1 = countUniloys.getSubMenu();
        countUniloy1.addItem("Uniloy 1", menuItemClickEvent -> {
            verticalLayout.removeAll();
            verticalLayout.add(countData.createChart("Uniloy 1"));
            if(countData.checkEfficiency("uniloy 1")) {
                verticalLayout.add(alarm.createAlarmLabel("Uniloy 1",
                        counterRepository.getCurrentMachineOut(),
                        counterRepository.getCurrentLeaktesterOut()));

                Button alert = new Button("Details");
                alert.addThemeVariants(ButtonVariant.LUMO_ERROR);
                alert.addClickListener(buttonClickEvent -> alarm.createDialog(counterRepository.getCurrentTrimmerIn(),
                        counterRepository.getCurrentTrimmerOut(), counterRepository.getCurrentLeaktesterIn(),
                        counterRepository.getCurrentLeaktesterOut()));
                verticalLayout.add(alert);
            }
        });

        countUniloy1.addItem("Uniloy 2", menuItemClickEvent -> {
            verticalLayout.removeAll();
            verticalLayout.add(countData.createChart("Uniloy 2"));
            if (countData.checkEfficiency("uniloy 2")) {
                verticalLayout.add(alarm.createAlarmLabel("Uniloy 2",
                        counterRepository.getU2CurrentMachineOut(),
                        counterRepository.getU2CurrentLeaktesterOut()));

                Button alert2 = new Button("Details");
                alert2.addThemeVariants(ButtonVariant.LUMO_ERROR);
                alert2.addClickListener(buttonClickEvent -> alarm.createDialog(counterRepository.getU2CurrentTrimmerIn(),
                        counterRepository.getU2CurrentTrimmerOut(), counterRepository.getU2CurrentLeaktesterIn(),
                        counterRepository.getU2CurrentLeaktesterOut()));
                verticalLayout.add(alert2);
            }
        });


        /*
        Trends
         */
        MenuItem trendsMenuItem = menuBar.addItem("Trends");
        SubMenu trendsSubMenu = trendsMenuItem.getSubMenu();
        MenuItem trendsUniloys = trendsSubMenu.addItem("Uniloys");
        SubMenu trendsUniloy1 = trendsUniloys.getSubMenu();
        trendsUniloy1.addItem("Uniloy 1", menuItemClickEvent -> {
            verticalLayout.removeAll();
            verticalLayout.add(trendData.createTrendChart("Uniloy 1",
                    trendRepo.getU1Total(),
                    trendRepo.getU1Dates()));
        });


        /*
        Downtime
         */
        MenuItem downtimeMenuItem = menuBar.addItem("Downtime");
        SubMenu downtimeSubMenu = downtimeMenuItem.getSubMenu();
        MenuItem downtimeUniloys = downtimeSubMenu.addItem("Uniloys");
        SubMenu downtimeUniloy1 = downtimeUniloys.getSubMenu();
        downtimeUniloy1.addItem("Uniloy 1", menuItemClickEvent -> {
            trendData.createData(2020, 02, 22);
        });


        /*
        Cycletimes
         */
        MenuItem cycle_timesMenuItem = menuBar.addItem("Cycle Times");
        SubMenu cycle_timesSubMenu = cycle_timesMenuItem.getSubMenu();
        MenuItem cycle_timesUniloys = cycle_timesSubMenu.addItem("Uniloys");
        SubMenu cycle_timesUniloy1 = cycle_timesUniloys.getSubMenu();
        cycle_timesUniloy1.addItem("Uniloy 1");

        add(menuBar);
    }
}
