package com.example.ashutoshshrivastava.currencyconverter;

import android.widget.Spinner;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by ashutoshshrivastava on 12/08/18.
 */

public interface CurrConvService {

    @GET("/exchange")
    Call<String> Currency(@Header("X-Mashape-Key") String header, @Query("from") String number, @Query("q") String value,@Query("to") String currTo);

}
