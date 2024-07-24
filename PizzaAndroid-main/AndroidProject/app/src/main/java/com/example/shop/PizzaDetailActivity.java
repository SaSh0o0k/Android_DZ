package com.example.shop;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shop.config.Config;
import com.example.shop.dto.PizzaItemDTO;
import com.example.shop.dto.PhotoDTO;
import com.example.shop.dto.IngredientDTO;
import com.example.shop.dto.SizeDTO;
import com.example.shop.network.PizzaApi;
import com.example.shop.network.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PizzaDetailActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView ratingTextView;
    private TextView availabilityTextView;
    private TextView categoryTextView;
    private LinearLayout photosLayout;
    private LinearLayout ingredientsLayout;
    private LinearLayout sizesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        nameTextView = findViewById(R.id.pizza_name);
        descriptionTextView = findViewById(R.id.pizza_description);
        ratingTextView = findViewById(R.id.pizza_rating);
        availabilityTextView = findViewById(R.id.pizza_availability);
        categoryTextView = findViewById(R.id.pizza_category);
        photosLayout = findViewById(R.id.pizza_photos);
        ingredientsLayout = findViewById(R.id.pizza_ingredients);
        sizesLayout = findViewById(R.id.pizza_sizes);

        int pizzaId = getIntent().getIntExtra("PIZZA_ID", -1);
        if (pizzaId != -1) {
            fetchPizzaDetails(pizzaId);
        }
    }

    private void fetchPizzaDetails(int pizzaId) {
        PizzaApi apiService = RetrofitClient.getInstance().getPizzaApi();
        Call<PizzaItemDTO> call = apiService.getById(pizzaId);

        call.enqueue(new Callback<PizzaItemDTO>() {
            @Override
            public void onResponse(Call<PizzaItemDTO> call, Response<PizzaItemDTO> response) {
                if (response.isSuccessful()) {
                    PizzaItemDTO pizza = response.body();
                    if (pizza != null) {
                        displayPizzaDetails(pizza);
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<PizzaItemDTO> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void displayPizzaDetails(PizzaItemDTO pizza) {
        nameTextView.setText(pizza.getName());
        descriptionTextView.setText(pizza.getDescription());
        ratingTextView.setText(String.format("Rating: %.1f", pizza.getRating()));
        availabilityTextView.setText(pizza.isAvailable() ? "Available" : "Not Available");
        categoryTextView.setText("Category: " + pizza.getCategory().getName());

        // Display all photos
        photosLayout.removeAllViews();
        for (PhotoDTO photo : pizza.getPhotos()) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 400); // Change size as needed
            params.setMargins(8, 0, 8, 0); // Add margin between images
            imageView.setLayoutParams(params);
            Picasso.get().load(Config.BASE_URL + "images/200_" + photo.getName()).into(imageView);
            photosLayout.addView(imageView);
        }

        // Display ingredients
        ingredientsLayout.removeAllViews();
        for (IngredientDTO ingredient : pizza.getIngredients()) {
            TextView ingredientView = new TextView(this);
            ingredientView.setText(ingredient.getName());
            ingredientsLayout.addView(ingredientView);
        }

        // Display sizes and prices
        sizesLayout.removeAllViews();
        for (SizeDTO size : pizza.getSizes()) {
            TextView sizeView = new TextView(this);
            sizeView.setText(size.getSizeName() + " см : ₴" + size.getPrice());
            sizesLayout.addView(sizeView);
        }
    }
}
