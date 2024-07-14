package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends BaseActivity {

    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(v -> {
            // Логіка для входу
        });

        registerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        setupBottomNavigationView(R.id.bottom_navigation);
    }
}
