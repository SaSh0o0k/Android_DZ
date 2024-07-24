package com.example.shop.pizza;

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
    private OnPizzaClickListener listener;

    public PizzasAdapter(List<PizzaItemDTO> items, OnPizzaClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PizzaCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaCardViewHolder holder, int position) {
        PizzaItemDTO pizza = items.get(position);
        holder.bind(pizza, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
