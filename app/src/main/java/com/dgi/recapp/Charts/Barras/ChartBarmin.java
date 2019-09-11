package com.dgi.recapp.Charts.Barras;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;

import java.util.ArrayList;

public class ChartBarmin {

    private String[] MINUTOS;
    private float[] DATA;

    public ChartBarmin(String[] MINUTOS, float[] DATA) {
        this.MINUTOS = MINUTOS;
        this.DATA = DATA;
    }
    private int[] colors =new int[]
            {Color.rgb(126,189,171),
                    Color.rgb(170,189,71),
                    Color.rgb(227,147,25),
                    Color.rgb(227,108,25),
                    Color.rgb(25,187,227)};
    private Chart getSameChart(Chart chart, String Descripcion, int textColor, int background, int animacionY)
    {

        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(background);
        chart.animateY(animacionY);
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setGranularityEnabled(false);
        chart.getXAxis().setGranularityEnabled(false);


        //Legend(chart);
        return chart;

    }

    public void createCharts(BarChart barChart)
    {
        barChart = (BarChart)getSameChart(barChart,"", Color.WHITE,Color.WHITE,0 );
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());


    }


    private ArrayList<BarEntry> getBarEntries()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i=0; i<DATA.length; i++)
        {
            entries.add(new BarEntry(i, DATA[i]));

        }
        return entries;

    }

    private void axisX(XAxis axis)
    {
        axis.setEnabled(false);

    }

    private void axisLeft(YAxis axis)
    {

        axis.setEnabled(false);

    }

    private void axisRight(YAxis axis)
    {
        axis.setEnabled(false);
    }



    private DataSet getData(DataSet dataSet)
    {
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);

        dataSet.setValueTextSize(0);

        return dataSet;
    }

    private BarData getBarData()
    {
        BarDataSet barDataSet =(BarDataSet) getData(new BarDataSet(getBarEntries(),""));

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.80f);
        return barData;
    }
}
