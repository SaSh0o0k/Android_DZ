package com.example.shop.pizza;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.R;

public class PizzaCardViewHolder extends RecyclerView.ViewHolder {
    private TextView pizzaName;
    private ImageView ivPizzaImage;

    private TextView price;


    public PizzaCardViewHolder(@NonNull View itemView) {
        super(itemView);
        pizzaName = itemView.findViewById(R.id.pizzaName);
        ivPizzaImage = itemView.findViewById(R.id.ivPizzaImage);
        price=itemView.findViewById(R.id.pizzaPrice);
    }

    public TextView getPizzaName() {
        return pizzaName;
    }

    public TextView getPrice() {
        return price;
    }

    public ImageView getIvPizzaImage() {
        return ivPizzaImage;
    }
}
