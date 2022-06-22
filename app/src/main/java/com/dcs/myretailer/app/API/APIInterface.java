package com.dcs.myretailer.app.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

//    @GET("/my_api/shop_list")
//    Example getPaymentMethod(@Query("companyCode") String companyCode);
    //?op=getPaymentMethod
    @GET("getPaymentMethod")
    Call<Example> getPaymentMethod(@Query("companyCode") String companyCode);

    @GET("movie/popular")
    Call<MovieModel> getPopularMovie(@Query("api_key") String api_key);

    @GET("users")
    Call<List<Example>> getUser();
//    Call<Example> getUser();
}
