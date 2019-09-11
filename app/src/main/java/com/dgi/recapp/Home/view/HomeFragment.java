package com.dgi.recapp.Home.view;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dgi.recapp.Api.Interface.ApiService;
import com.dgi.recapp.Api.Model.Menu;
import com.dgi.recapp.Api.RetrofitBuilder;
import com.dgi.recapp.Api.TokenManager;
import com.dgi.recapp.ContainerActivity;
import com.dgi.recapp.CurrentAccount.view.ChartCurrentAccountFragment;
import com.dgi.recapp.LoginActivity;
import com.dgi.recapp.R;
import com.dgi.recapp.adapter.MenuAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    private RecyclerView recyclerView;
    protected MenuAdapter menuAdapter;
    private ApiService service;
    private TokenManager tokenManager;
    Call<List<Menu>> call;
    private String TAG="HOMEMENU";
    boolean isExecute =false;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout =(SwipeRefreshLayout) view.findViewById(R.id.refreshcard);

        tokenManager = TokenManager.getInstance(view.getContext().getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(view.getContext(), LoginActivity.class));

        }
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);


        showToolbar("Home",false,view);

        getMenu(view);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMenu(view);
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        return view;
    }

    //@OnClick(R.id.pictureMenuCC) void CiclkOption()
    //{

     //   SwitchFragment(new ChartCurrentAccountFragment());
    //}

    private void getMenu(View view)
    {

        recyclerView = (RecyclerView) view.findViewById(R.id.Menurecycler);
        menuAdapter = new MenuAdapter(view.getContext(), this);
        recyclerView.setAdapter(menuAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        call = service.getMenu();

        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                isExecute=true;
                Log.w(TAG , "onResponse: " + response );
                if(response.isSuccessful()){

                    menuAdapter.addItemList(response.body());
                    for (Menu menu: response.body() ) {
                        Log.w(TAG , "Success: " + menu.getTitulo() );
                    }
                }else {
                    if (response.code() == 422) {
                        // handleErrors(response.errorBody());
                        Toast.makeText(view.getContext(),"No esta Autorizado", Toast.LENGTH_LONG).show();
                    }
                    if (response.code() == 401) {
                        //ApiError apiError = Utils.converErrors(response.errorBody());
                        Toast.makeText(view.getContext(),"No esta Autorizado", Toast.LENGTH_LONG).show();

                    }
                    swipeRefreshLayout.setRefreshing(false);
                    tokenManager.deleteToken();


                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //if(!isExecute)
        //{
         //   tokenManager.deleteToken();
        //}



    }


    private void showToolbar(String title,boolean upButton, View view)
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
