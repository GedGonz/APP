package com.dgi.recapp.CurrentAccount.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgi.recapp.ContainerActivity;
import com.dgi.recapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartCurrentAccountFragment extends Fragment {


    public ChartCurrentAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_current_account, container, false);

        showToolbar("Recaudación", true,view);
        return view;
        // Inflate the layout for this fragment

    }



    public void showToolbar(String title,boolean upButton, View view)
    {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().onBackPressed();
                startActivity(new Intent(view.getContext(), ContainerActivity.class));

            }
        });
    }


}
