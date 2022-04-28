package com.dcs.myretailer.app.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    //14376814342aa7e16227a29749cebee3
    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    private static Retrofit retrofit = null;
    //private static String BASE_URL = "https://myretailer360.synthesis.bz:44309/myRetailerAPI.asmx/";
    //private static String BASE_URL = "https://api.themoviedb.org/3/";
    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";

//    https://jsonplaceholder.typicode.com/users

    public static Retrofit getRetrofit() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
//
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;

//        HttpUrl baseUrl = new HttpUrl.Builder()
//                .scheme("https")
//                .host("www.hbapimanager.azure-api.net")
//                .encodedPath("/Wallet/")
//                .build();
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new AdditionalHttpHeadersInterceptor(headersProvider))
//                .addInterceptor(httpLoggingInterceptor)
//                .build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

//        return retrofit;
    }
}
