package com.dgi.recapp.Charts.Barras;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class ChartBarDetails {

    private String[] MINUTOS;
    private float[] DATA;

    public ChartBarDetails(String[] MINUTOS, float[] DATA) {
        this.MINUTOS = MINUTOS;
        this.DATA = DATA;
    }

    private int[] colors =new int[]
            {Color.rgb(126,189,171),
                    Color.rgb(170,189,71),
                    Color.rgb(227,147,25),
                    Color.rgb(227,108,25),
                    Color.rgb(25,187,227)};
    private Chart getSameChart2(Chart chart, String Descripcion, int textColor, int background, int animacionY)
    {
        chart.getDescription().setText(Descripcion);
        chart.getDescription().setTextSize(15);
        chart.getDescription().setTextColor(textColor);
        chart.setBackgroundColor(background);
        chart.animateY(animacionY);
        Legend(chart);
        return chart;

    }

    public void createCharts(BarChart barChart)
    {
        barChart = (BarChart)getSameChart2(barChart,"Series", Color.WHITE,Color.WHITE,3000 );
        barChart.setDrawGridBackground(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight2(barChart.getAxisRight());


    }
    private void Legend(Chart chart)
    {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry> entries = new ArrayList<>();
        for(int i= 0; i< MINUTOS.length; i++)
        {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors[i];
            entry.label = MINUTOS[i] ;
            entries.add(entry);
        }
        legend.setCustom(entries);

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
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(MINUTOS));

    }

    private void axisLeft(YAxis axis)
    {
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);

    }

    private void axisRight2(YAxis axis)
    {
        axis.setEnabled(false);
    }



    private DataSet getData(DataSet dataSet)
    {
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10);

        return dataSet;
    }

    private BarData getBarData()
    {
        BarDataSet barDataSet =(BarDataSet) getData(new BarDataSet(getBarEntries(),""));

        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }
}
