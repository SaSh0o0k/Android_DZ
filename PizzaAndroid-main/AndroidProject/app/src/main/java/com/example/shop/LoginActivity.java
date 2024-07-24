package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shop.application.HomeApplication;
import com.example.shop.dto.account.LoginDTO;
import com.example.shop.dto.account.LoginResponseDTO;
import com.example.shop.network.RetrofitClient;
import com.example.shop.security.JwtSecurityService;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    Button loginBtn, registerBtn;
    private TextInputEditText etLoginEmail;
    private TextInputEditText etLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        JwtSecurityService jwt = HomeApplication.getInstance();
        jwt.deleteToken();

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);

//        loginBtn.setOnClickListener(v -> {
//            // Логіка для входу
//        });

//        registerBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        });

        setupBottomNavigationView(R.id.bottom_navigation);
    }

    public void onClickLogin(View view) {
        LoginDTO model = new LoginDTO();
        model.setEmail(etLoginEmail.getText().toString());
        model.setPassword(etLoginPassword.getText().toString());
        RetrofitClient.getInstance()
                .getAccountApi()
                .login(model)
                .enqueue(new Callback<LoginResponseDTO>() {
                    @Override
                    public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                        if(response.isSuccessful()) {
                            LoginResponseDTO result = response.body();
                            String token = result.getToken();
                            JwtSecurityService jwt = HomeApplication.getInstance();
                            jwt.saveJwtToken(token);
                            Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
                            startActivity(intent);
                        }
                        else {
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("API Error", "Error: " + errorBody);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponseDTO> call, Throwable throwable) {
                        int a = 10;
                        a++;
                    }
                });
    }


}
