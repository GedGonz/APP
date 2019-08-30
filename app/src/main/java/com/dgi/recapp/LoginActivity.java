package com.dgi.recapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.dgi.recapp.Api.Interface.IFichaAuditoria;
import com.dgi.recapp.Api.Model.FichaModel;
import com.dgi.recapp.Api.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "RETROFIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new RetrofitAPI().getCliente();
        IFichaAuditoria ficha = retrofit.create(IFichaAuditoria.class);

        Call<FichaModel> call = ficha.getFicha();

        call.enqueue(new Callback<FichaModel>() {
            @Override
            public void onResponse(Call<FichaModel> call, Response<FichaModel> response) {
                if(!response.isSuccessful())
                {
                    Log.e(TAG,"Codigo: "+ response.code());
                }

                FichaModel ficha= response.body();
                Log.e(TAG, ficha.getRuc());
            }

            @Override
            public void onFailure(Call<FichaModel> call, Throwable t) {
                Log.e(TAG,"Error: "+ t.getMessage());
            }
        });
    }
}
