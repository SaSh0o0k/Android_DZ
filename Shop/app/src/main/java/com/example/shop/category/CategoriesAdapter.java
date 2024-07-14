package com.example.shop.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shop.R;
import com.example.shop.config.Config;
import com.example.shop.dto.CategoryItemDTO;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryCardViewHolder> {
    private List<CategoryItemDTO> items;
    private OnCategoryClickListener listener;

    public CategoriesAdapter(List<CategoryItemDTO> items, OnCategoryClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        return new CategoryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCardViewHolder holder, int position) {
        if (items != null && position < items.size()) {
            CategoryItemDTO item = items.get(position);
            holder.getCategoryName().setText(item.getName());
            // Формування повного шляху до зображення
            String imageUrl = Config.BASE_URL + "images/" + item.getImage() + "?timestamp=" + System.currentTimeMillis();
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    //.apply(new RequestOptions().override(400))
                    .into(holder.getIvCategoryImage());

            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCategoryClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }
}
