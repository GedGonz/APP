package com.dgi.recapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgi.recapp.Api.Model.Menu;
import com.dgi.recapp.ContainerActivity;
import com.dgi.recapp.CurrentAccount.view.ChartCurrentAccountFragment;
import com.dgi.recapp.Home.view.HomeFragment;
import com.dgi.recapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>
{
    public ArrayList<Menu> dataset;
    public Context context;

    private String[] MESES =new String[]{"Enero","Febrero","Marzo"};
    private int[] Declaracion =new int[]{498, 500, 505};
    private int[] colors =new int[]
            {Color.rgb(126,189,171),
                    Color.rgb(170,189,71),
                    Color.rgb(227,147,25),
                    Color.rgb(227,108,25),
                    Color.rgb(25,187,227)};


    public MenuAdapter( Context context) {
        this.dataset = new ArrayList<>();
        this.context = context;

    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_menu, parent, false);
        MenuAdapter.MenuViewHolder menuViewHolder = new MenuAdapter.MenuViewHolder(view ,dataset);

        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        final Menu menu = dataset.get(position);
        String IdMenu= String.valueOf(menu.getId());
        holder.TituloMenu.setText(menu.getTitulo());
        holder.DescripcionMenu.setText(menu.getDescripcion());
        createCharts(holder.barChart);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ContainerActivity.class);
                intent.putExtra("idmenu", IdMenu);

                context.startActivity(intent);

            }
        });

    }


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


    private ArrayList<BarEntry> getBarEntries()
    {
        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i=0; i<Declaracion.length; i++)
        {
            entries.add(new BarEntry(i, Declaracion[i]));

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

    public void createCharts(BarChart barChart)
    {
        barChart = (BarChart)getSameChart(barChart,"",Color.WHITE,Color.WHITE,0 );
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());


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

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addItemList(List<Menu> Menus)
    {
        dataset.addAll(Menus);
        notifyDataSetChanged();
    }



    public static class MenuViewHolder extends RecyclerView.ViewHolder
    {
        TextView TituloMenu, DescripcionMenu;
        ImageView ImageIconMenu;
        List<Menu> Menus;
        BarChart barChart;

        public MenuViewHolder(@NonNull View view, List<Menu> Menus) {
            super(view);
            this.TituloMenu = (TextView)view.findViewById(R.id.TituloMenu);
            this.DescripcionMenu = (TextView)view.findViewById(R.id.DescripcionMenu);
            barChart = (BarChart) view.findViewById(R.id.barChart);
            this.Menus = Menus;
        }
    }



}
