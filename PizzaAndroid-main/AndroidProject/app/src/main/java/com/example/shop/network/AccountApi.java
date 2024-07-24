package com.example.shop.network;

import com.example.shop.dto.account.LoginDTO;
import com.example.shop.dto.account.LoginResponseDTO;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AccountApi {
    @POST("/api/accounts/signin")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);

    @Multipart
    @POST("/api/accounts/registration")
    Call<Void> registerUser(
            @Part("firstName") RequestBody firstName,
            @Part("lastName") RequestBody lastName,
            @Part MultipartBody.Part image,
            @Part("email") RequestBody email,
            @Part("userName") RequestBody userName,
            @Part("password") RequestBody password
    );
}
