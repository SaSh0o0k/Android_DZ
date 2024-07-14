package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.category.CategoriesAdapter;
import com.example.shop.category.OnCategoryClickListener;
import com.example.shop.dto.CategoryItemDTO;
import com.example.shop.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends BaseActivity implements OnCategoryClickListener {

    RecyclerView rcCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rcCategories = findViewById(R.id.rcCategories);
        rcCategories.setHasFixedSize(true);
        rcCategories.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));

        RetrofitClient
                .getInstance()
                .getCategoriesApi()
                .list()
                .enqueue(new Callback<List<CategoryItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                        List<CategoryItemDTO> items = response.body();
                        if (items != null) {
                            CategoriesAdapter ca = new CategoriesAdapter(items, CategoryActivity.this);
                            rcCategories.setAdapter(ca);
                        } else {
                            // Handle the case when response body is null
                            // For example, you can log an error or show a message to the user
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CategoryItemDTO>> call, Throwable throwable) {
                        // Handle the failure case
                        // For example, you can log an error or show a message to the user
                    }
                });

        setupBottomNavigationView(R.id.bottom_navigation);
    }

    @Override
    public void onCategoryClick(CategoryItemDTO category) {
        if (category.getName().equalsIgnoreCase("Піца")) {
            // Відкрийте нову активність для відображення піц
            Intent intent = new Intent(this, PizzaActivity.class);
            startActivity(intent);
        }
    }
}
