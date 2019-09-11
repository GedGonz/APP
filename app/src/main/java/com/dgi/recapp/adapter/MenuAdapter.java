package com.dgi.recapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.dgi.recapp.Api.Model.Menu;
import com.dgi.recapp.Charts.Barras.ChartBarDetails;
import com.dgi.recapp.Charts.Barras.ChartBarmin;
import com.dgi.recapp.R;
import com.github.mikephil.charting.charts.BarChart;
import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>
{
    public ArrayList<Menu> dataset;
    public Context context;
    public Fragment fragment;



    public MenuAdapter( Context context, Fragment fragment) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.fragment=fragment;

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
        holder.DescripcionMenu.setText(ValuMax(menu.getData().getDatos()));

        new ChartBarmin(menu.getData().getMinutos(),menu.getData().getDatos()).createCharts(holder.barChart);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(context, ContainerActivity.class);
                //intent.putExtra("idmenu", IdMenu);

                //context.startActivity(intent);


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View newview =fragment.getLayoutInflater().inflate(R.layout.chart_dialog,null);
                TextView cardChartTitulo = (TextView)newview.findViewById(R.id.CardChartTitulo);

                BarChart barChart = (BarChart) newview.findViewById(R.id.barChartDialog);
                cardChartTitulo.setText(menu.getTitulo());
                new ChartBarDetails(menu.getData().getMinutos(),menu.getData().getDatos()).createCharts(barChart);

                builder.setView(newview);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }


    private String ValuMax(float[] array)
    {
        float numeromayor=0.0f;
        for(int i=0; i<array.length; i++){
            // System.out.println(nombres[i] + " " + sueldos[i]);
            if(array[i]>numeromayor){ //
                numeromayor = array[i];
            }
        }

        return String.valueOf(numeromayor);
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
