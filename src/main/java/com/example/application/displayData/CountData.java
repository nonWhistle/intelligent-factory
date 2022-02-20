package com.example.application.displayData;

import com.example.application.repositories.CounterRepository;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;

public class CountData
{
    private final CounterRepository counterRepository;

    public CountData(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public Chart createChart(String machine)
    {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration configuration = chart.getConfiguration();
        configuration.setTitle(machine);

        XAxis xAxis = configuration.getxAxis();
        xAxis.setCategories("Current", "Shift 1", "Shift 2");

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle("Number of bottles");

        Tooltip tooltip = configuration.getTooltip();
        tooltip.setPointFormat("{series.name}: {point.y}");

        switch(machine) {
            case "Uniloy 1":
                configuration.addSeries(new ListSeries("Machine output", counterRepository.getCurrentMachineOut(),
                        counterRepository.getShift1MachineOut(), counterRepository.getShift2MachineOut()));
                configuration.addSeries(new ListSeries("Trimmer input", counterRepository.getCurrentTrimmerIn(),
                        counterRepository.getShift1TrimmerIn(), counterRepository.getShift2TrimmerIn()));
                configuration.addSeries(new ListSeries("Trimmer output", counterRepository.getCurrentTrimmerOut(),
                        counterRepository.getShift1TrimmerOut(), counterRepository.getShift2TrimmerOut()));
                configuration.addSeries(new ListSeries("Leaktester input", counterRepository.getCurrentLeaktesterIn(),
                        counterRepository.getShift1LeaktesterIn(), counterRepository.getShift2LeaktesterIn()));
                configuration.addSeries(new ListSeries("Leaktester output", counterRepository.getCurrentLeaktesterOut(),
                        counterRepository.getShift1LeaktesterOut(), counterRepository.getShift2LeaktesterOut()));
                break;
            case "Uniloy 2":
                configuration.addSeries(new ListSeries("Machine output", counterRepository.getU2CurrentMachineOut(),
                        counterRepository.getU2Shift1MachineOut(), counterRepository.getU2Shift2MachineOut()));
                configuration.addSeries(new ListSeries("Trimmer input", counterRepository.getU2CurrentTrimmerIn(),
                        counterRepository.getU2Shift1TrimmerIn(), counterRepository.getU2Shift2TrimmerIn()));
                configuration.addSeries(new ListSeries("Trimmer output", counterRepository.getU2CurrentTrimmerOut(),
                        counterRepository.getU2Shift1TrimmerOut(), counterRepository.getU2Shift2TrimmerOut()));
                configuration.addSeries(new ListSeries("Leaktester input", counterRepository.getU2CurrentLeaktesterIn(),
                        counterRepository.getU2Shift1LeaktesterIn(), counterRepository.getU2Shift2LeaktesterIn()));
                configuration.addSeries(new ListSeries("Leaktester output", counterRepository.getU2CurrentLeaktesterOut(),
                        counterRepository.getU2Shift1LeaktesterOut(), counterRepository.getU2Shift2LeaktesterOut()));
                break;
            default:
                configuration.addSeries(new ListSeries("error", 0,0,0));
        }

        return chart;
    }

    public boolean checkEfficiency(String machine)
    {

        return switch (machine) {
            case "Uniloy 1" -> !(((double) counterRepository.getCurrentLeaktesterOut() /
                    (double) counterRepository.getCurrentMachineOut()) < 0.98);
            case "Uniloy 2" -> !(((double) counterRepository.getU2CurrentLeaktesterOut() /
                    (double) counterRepository.getU2CurrentMachineOut()) < 0.98);
            default -> true;
        };
    }
}
