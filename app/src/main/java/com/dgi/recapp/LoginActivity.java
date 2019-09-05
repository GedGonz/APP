package com.dgi.recapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.TransitionManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dgi.recapp.Api.Interface.ApiService;
import com.dgi.recapp.Api.Model.AccessToken;
import com.dgi.recapp.Api.RetrofitBuilder;
import com.dgi.recapp.Api.TokenManager;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "RETROFIT";
    ApiService service;
    TokenManager tokenManager;
    Call<AccessToken> call;


    @BindView(R.id.username) TextInputEditText username;
    @BindView(R.id.password) TextInputEditText password;
    @BindView(R.id.progresbarLogin)
    ProgressBar loader;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = RetrofitBuilder.createService(ApiService.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        ButterKnife.bind(this);

        if(tokenManager.getToken().getAccess_token() != null){
            startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
            finish();
        }
    }

    @OnClick(R.id.btnLogin) void submit() {

        String UserName = username.getText().toString().trim();
        String Password = password.getText().toString().trim();

        //tilEmail.setError(null);
        //tilPassword.setError(null);

        //validator.clear();

        if (true) {
            showLoading();
            call = service.login(UserName, Password,"password");
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                    Log.w(TAG, "onResponse: " + response);

                    if (response.isSuccessful()) {

                        AccessToken token = response.body();

                        tokenManager.saveToken(token);
                        Log.w(TAG, "Token: " +token.getAccess_token());
                        startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Error en inicio de sesion", Toast.LENGTH_LONG).show();
                        if (response.code() == 422) {
                           // handleErrors(response.errorBody());
                        }
                        if (response.code() == 401) {
                            //ApiError apiError = Utils.converErrors(response.errorBody());

                        }


                    }
                    showForm();
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(LoginActivity.this,"No se pudo establecer comunicación", Toast.LENGTH_LONG).show();
                    showForm();
                }
            });

        }
    }

    private void showLoading(){

        loader.setVisibility(View.VISIBLE);
    }

    private void showForm(){

        loader.setVisibility(View.GONE);
    }


}
