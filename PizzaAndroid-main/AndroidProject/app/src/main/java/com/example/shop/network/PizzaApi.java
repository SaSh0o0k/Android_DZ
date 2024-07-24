package com.example.shop.network;

import com.example.shop.dto.PizzaItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PizzaApi {
    @GET("/api/pizza/getAll")
    Call<List<PizzaItemDTO>> list();

    @GET("/api/pizza/getByCategory")
    Call<List<PizzaItemDTO>> listByCategory(@Query("category") String category);

    @GET("/api/pizza/getById/{id}")
    Call<PizzaItemDTO> getById(@Path("id") int id);
}
