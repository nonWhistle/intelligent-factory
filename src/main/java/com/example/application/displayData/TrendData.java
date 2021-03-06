package com.example.application.displayData;

import com.example.application.repositories.TrendRepo;
import com.example.application.views.production.Machine;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TrendData
{
    private final TrendRepo trendRepo;
    private final Random random;

    public TrendData(TrendRepo trendRepo)
    {
        this.trendRepo = trendRepo;
        random = new Random();
    }

    public Chart createTrendChart(String machine, List<Double> intList, List<Date> dateList)
    {
        Chart chart = new Chart(ChartType.SPLINE);
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText(machine + " Weekly Efficiency Rate");
        configuration.getTooltip().setEnabled(true);
        configuration.getTooltip().setPointFormat("Weeks Efficiency: {point.y}");

        DataSeries dataSeries = new DataSeries();

        for (int i = 0; i < trendRepo.getU1Total().size(); i++) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(dateList.get(i));
            item.setY(intList.get(i));
            dataSeries.add(item);
        }

        configuration.addSeries(dataSeries);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(1);
        configuration.setRangeSelector(rangeSelector);

        return chart;
    }

    public void createData(int yyyy, int mm, int dd)
    {
        LocalDate localDate = LocalDate.of(yyyy, mm, dd);

        for(int i = 0; i < 100; i++) {
            Machine machine = new Machine();

            LocalDate date = localDate.plusWeeks(i);
            machine.setU2_date(date);

            double machineEff = random.nextDouble(0.90, 0.99);
            int scale = (int) Math.pow(10, 2);
            double outputEfficiency = (double) Math.round(machineEff * scale) / scale;
            machine.setU2_output(outputEfficiency);

            trendRepo.insertForU2(machine);
        }
    }
}
