package com.example.shop.pizza;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.config.Config;
import com.example.shop.dto.PizzaItemDTO;

import java.util.List;

public class PizzasAdapter extends RecyclerView.Adapter<PizzaCardViewHolder> {
    private List<PizzaItemDTO> items;

    public PizzasAdapter(List<PizzaItemDTO> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PizzaCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_pizza, parent, false);
        return new PizzaCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaCardViewHolder holder, int position) {
        if (items != null && position < items.size()) {
            PizzaItemDTO item = items.get(position);

            Log.d("ItemPizza:","onBindViewHolder: "+item.getFirstImage());
            holder.getPizzaName().setText(item.getName());
            double price =item.getSizes().get(0).getPrice();
            holder.getPrice().setText( String.valueOf(price)+" ₴");
            String imageUrl = Config.BASE_URL + "images/200_" + item.getFirstImage() + "?timestamp=" + System.currentTimeMillis();
            Log.d("ItemUrl:","onBindViewHolder: "+imageUrl);
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(holder.getIvPizzaImage());
        }
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }
}
