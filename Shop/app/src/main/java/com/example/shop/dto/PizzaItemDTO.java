package com.example.shop.dto;

import java.util.List;

public class PizzaItemDTO {
    private int id;
    private String name;
    private String description;
    private double rating;
    private boolean isAvailable;
    private CategoryItemDTO category;
    private List<PhotoDTO> photos;
    private List<IngredientDTO> ingredients;
    private List<SizeDTO> sizes;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public CategoryItemDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryItemDTO category) {
        this.category = category;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<SizeDTO> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeDTO> sizes) {
        this.sizes = sizes;
    }

    public String getFirstImage() {
        if (photos != null && !photos.isEmpty()) {
            return photos.get(0).getName();
        }
        return null;
    }

}
