package com.example.application.displayData;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

public class TrendData
{
    ArrayList<Integer> bottles = new ArrayList<>();
    Random random = new Random();

    public TrendData()
    {
        createData();
    }

    public Chart createTrendChart(String machine)
    {
        Chart chart = new Chart(ChartType.SPLINE);
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText(machine + " Bottle Production");
        configuration.getTooltip().setEnabled(true);

        DataSeries dataSeries = new DataSeries();

        /*
        for (int  data: bottles)
        {
            DataSeriesItem item = new DataSeriesItem();

            item.setX();
            item.setY(data);
            dataSeries.add(item);
        }

         */

        configuration.addSeries(dataSeries);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(1);
        configuration.setRangeSelector(rangeSelector);

        return chart;
    }

    public void createData()
    {
        for(int i = 0; i < 10000; i++){
            bottles.add(random.nextInt(40000) + 30000);
        }
    }
}
