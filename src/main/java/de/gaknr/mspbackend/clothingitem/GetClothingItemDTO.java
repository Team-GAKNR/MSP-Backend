package de.gaknr.mspbackend.clothingitem;

public record GetClothingItemDTO(
    int id,
    String name,
    byte[] image,
    String brand,
    String color,
    MasterCategory masterCategory,
    SubCategory subCategory,
    String type,
    String season,
    String usage,
    boolean isFavorite
) {
}
