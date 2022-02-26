package com.example.application.displayData;

import com.example.application.repositories.MachinesOnRepo;
import com.example.application.views.production.MachinesOn;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class OnOffData
{
    private final MachinesOnRepo machinesOnRepo;

    public OnOffData(MachinesOnRepo machinesOnRepo) {
        this.machinesOnRepo = machinesOnRepo;
    }

    public VerticalLayout createSpan(int firstMachine, int lastMachine)
    {
        VerticalLayout vLayout = new VerticalLayout();

        int start = firstMachine;
        int end = lastMachine;
        while(start <= end) {
            String machine = "uniloy" + start;
            MachinesOn machinesOn = new MachinesOn();
            machinesOn.setMachine(machine);
            boolean isRunning = machinesOnRepo.getSelected(machinesOn);
            String onOrOff;
            String theme;
            HorizontalLayout hLayout = new HorizontalLayout();
            hLayout.setPadding(true);

            if(isRunning){
                onOrOff = "On";
                theme = "badge success";
            }
            else{
                onOrOff = "Off";
                theme = "badge error";
            }

            Span onOff = new Span(onOrOff);
            onOff.setWidth("150px");
            onOff.getElement().getThemeList().add(theme);

            Span span = new Span("Uniloy " + start);
            span.getElement().getThemeList().add("badge contrast");
            span.setWidth("200px");
            hLayout.add(span, onOff);

            vLayout.add(hLayout);

            start++;
        }
        return vLayout;
    }
}
