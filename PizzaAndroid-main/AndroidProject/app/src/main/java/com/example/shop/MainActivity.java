package com.example.shop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.shop.config.Config;
import com.example.shop.dto.account.RegisterDTO;
import com.example.shop.network.RetrofitClient;
import com.example.shop.network.AccountApi;
import com.example.shop.services.FileUtils;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageView;
    private Button registerBtn;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText userNameInput;
    private EditText passwordInput;
    private Button chooseImageBtn;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.ivRegImage);
        chooseImageBtn = findViewById(R.id.btnChooseImage);
        registerBtn = findViewById(R.id.register_btn);

        firstNameInput = findViewById(R.id.etRegFirstName);
        lastNameInput = findViewById(R.id.etRegLastName);
        emailInput = findViewById(R.id.etRegEmail);
        userNameInput = findViewById(R.id.etRegUserName);
        passwordInput = findViewById(R.id.etRegPassword);
        String url = Config.BASE_URL+"images/corporate-user-icon.webp" + "?timestamp=" + System.currentTimeMillis();
        Glide.with(this)
                .load(url)
                .transform(new CircleCrop())
                .into(imageView);

        registerBtn.setOnClickListener(v -> registerUser());

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
            imageUri = data.getData();
            Glide.with(this)
                    .load(imageUri)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    private void registerUser() {
        if (imageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setFirstName(firstNameInput.getText().toString());
        registerDTO.setLastName(lastNameInput.getText().toString());
        registerDTO.setEmail(emailInput.getText().toString());
        registerDTO.setUserName(userNameInput.getText().toString());
        registerDTO.setPassword(passwordInput.getText().toString());
        registerDTO.setImageUri(imageUri.toString());

        MultipartBody.Part imagePart = prepareImagePart(registerDTO.getImageUri());
        if (imagePart == null) {
            Toast.makeText(this, "Invalid image file", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountApi apiService = RetrofitClient.getInstance().getAccountApi();
        Call<Void> call = apiService.registerUser(
                createPartFromString(registerDTO.getFirstName()),
                createPartFromString(registerDTO.getLastName()),
                imagePart,
                createPartFromString(registerDTO.getEmail()),
                createPartFromString(registerDTO.getUserName()),
                createPartFromString(registerDTO.getPassword())
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    Log.e("RegisterUser", "Registration failed: " + response.message());
                    Log.e("RegisterUser", "Response code: " + response.code());
                    Log.e("RegisterUser", "Error body: " + response.errorBody().toString());
                    Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("RegisterUser", "An error occurred: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private MultipartBody.Part prepareImagePart(String imageUri) {
        String imagePath = FileUtils.getPath(this, Uri.parse(imageUri));
        if (imagePath == null) {
            return null;
        }
        File file = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(Uri.parse(imageUri))), file);
        return MultipartBody.Part.createFormData("image", file.getName(), requestFile);
    }

    private RequestBody createPartFromString(String value) {
        return RequestBody.create(MultipartBody.FORM, value);
    }
}
