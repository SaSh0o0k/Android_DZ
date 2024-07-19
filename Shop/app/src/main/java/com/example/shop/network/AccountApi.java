package com.example.shop.network;

import com.example.shop.dto.account.LoginDTO;
import com.example.shop.dto.account.LoginResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/accounts/signin")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);
}