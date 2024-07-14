package com.example.shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    ImageView imageView;
    Button registerBtn;
    EditText usernameInput;
    EditText phoneInput;
    Button updateProfileBtn;
    Button chooseImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageView = findViewById(R.id.ivRegImage);
        chooseImageBtn = findViewById(R.id.btnChooseImage);
        registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

//        String url = "http://10.0.2.2:5174/images/corporate-user-icon.webp";
        String url = "https://android.tohaproject.click/images/corporate-user-icon.webp"+"?timestamp="+System.currentTimeMillis();
        Glide.with(this)
                .load(url)
                .transform(new CircleCrop())
                .into(imageView);

        chooseImageBtn.setOnClickListener(v -> openImagePicker());

        setupBottomNavigationView(R.id.bottom_navigation);
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Glide.with(this)
                    .load(uri)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }
}
