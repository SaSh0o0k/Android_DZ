package com.example.shop.network;

import com.example.shop.dto.PizzaItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PizzaApi {
    @GET("/api/pizza/getAll")
    Call<List<PizzaItemDTO>> list();
}
