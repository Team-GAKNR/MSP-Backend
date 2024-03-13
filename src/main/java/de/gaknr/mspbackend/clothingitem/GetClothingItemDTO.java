package de.gaknr.mspbackend.clothingitem;

import java.util.Base64;

public record GetClothingItemDTO(
    int id,
    String name,
    Base64 image,
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
