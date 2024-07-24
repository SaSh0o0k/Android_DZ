package com.example.shop.pizza;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.config.Config;
import com.example.shop.dto.PizzaItemDTO;

public class PizzaCardViewHolder extends RecyclerView.ViewHolder {

    private TextView pizzaName;
    private ImageView ivPizzaImage;
    private TextView price;

    public PizzaCardViewHolder(@NonNull View itemView) {
        super(itemView);
        pizzaName = itemView.findViewById(R.id.pizzaName);
        ivPizzaImage = itemView.findViewById(R.id.ivPizzaImage);
        price = itemView.findViewById(R.id.pizzaPrice);
    }

    public void bind(PizzaItemDTO pizza, OnPizzaClickListener listener) {
        pizzaName.setText(pizza.getName());
        double pizzaPrice = pizza.getSizes().get(0).getPrice();
        price.setText(String.valueOf(pizzaPrice) + " ₴");

        String imageUrl = Config.BASE_URL + "images/200_" + pizza.getFirstImage() + "?timestamp=" + System.currentTimeMillis();
        Glide.with(itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(ivPizzaImage);

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPizzaClick(pizza);
            }
        });
    }
}
