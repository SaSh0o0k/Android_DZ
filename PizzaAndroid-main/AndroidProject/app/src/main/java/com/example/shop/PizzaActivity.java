package com.example.shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.dto.PizzaItemDTO;
import com.example.shop.pizza.OnPizzaClickListener;
import com.example.shop.pizza.PizzasAdapter;
import com.example.shop.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PizzaActivity extends BaseActivity implements OnPizzaClickListener {

    RecyclerView rcPizzas;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        // Отримання категорії з інтенції
        category = getIntent().getStringExtra("CATEGORY_NAME");

        rcPizzas = findViewById(R.id.rcPizzas);
        rcPizzas.setHasFixedSize(true);
        rcPizzas.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));

        RetrofitClient
                .getInstance()
                .getPizzaApi()
                .listByCategory(category)
                .enqueue(new Callback<List<PizzaItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<PizzaItemDTO>> call, Response<List<PizzaItemDTO>> response) {
                        List<PizzaItemDTO> items = response.body();
                        if (items != null) {
                            PizzasAdapter pa = new PizzasAdapter(items, PizzaActivity.this);
                            rcPizzas.setAdapter(pa);
                        } else {
                            // Handle the case when response body is null
                            // For example, you can log an error or show a message to the user
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PizzaItemDTO>> call, Throwable throwable) {
                        // Handle the failure case
                        // For example, you can log an error or show a message to the user
                    }
                });

        setupBottomNavigationView(R.id.bottom_navigation);
    }

    @Override
    public void onPizzaClick(PizzaItemDTO pizza) {
        // Handle click on pizza item
        Intent intent = new Intent(PizzaActivity.this, PizzaDetailActivity.class);
        intent.putExtra("PIZZA_ID", pizza.getId());
        startActivity(intent);
    }
}
