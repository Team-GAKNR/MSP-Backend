package de.gaknr.mspbackend.clothingitem;

import org.bson.types.ObjectId;

public record GetClothingItemDTO(
    ObjectId id,
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
