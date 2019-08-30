package com.dgi.recapp.Api;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {

    private String URL = "http://10.16.216.220:54367/";
    private static Retrofit retrofit=null;
    private static final String TOKEN="bearer QUSUdWGUKA6KJfNkDQr5nQHvXeq57mYD9Sj9mBzzb2WdAPqN791Y9XtspVsNipS8y291tzE9oN0oA7b9ALUOZxWA2x_G5Zhmgt2hutH9sw-w5kHLdXAKe0FUJSenoOaTGlLkHZhu6dOi8KHUDW-P_DqQr8QDhWK0eGHnSI1ChlftqGgbWuFK3D39mcFEssqCWBpZfKLTFKUxngr-xjw3XpWmeRMDIHSW_WPsszxroUuZtOgzuPLOnk7tgniaYWWqXHhSYxCjCj5vmoGEP_NTnonloeVPL5Nf1TAhAjSFkQjg-zts-rxDvRqczRQtyt0f";

    public Retrofit getCliente()
    {
        if(retrofit==null)
        {
            /*
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder().
                            addHeader("Content-Type", "application/json").
                            addHeader("Authorization",TOKEN)

                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();*/
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.client(client)
                    .build();
        }

       return retrofit;
    }
}
