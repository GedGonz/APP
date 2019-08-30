package com.dgi.recapp.Api.Interface;

import com.dgi.recapp.Api.Model.FichaModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IFichaAuditoria {

    @POST("api/FichaAuditoria")
    Call<FichaModel> getFicha();
}
