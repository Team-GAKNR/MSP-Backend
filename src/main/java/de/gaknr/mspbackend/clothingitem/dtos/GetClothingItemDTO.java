package de.gaknr.mspbackend.clothingitem.dtos;

import de.gaknr.mspbackend.clothingitem.enums.*;
import org.bson.types.ObjectId;

public record GetClothingItemDTO(
    ObjectId id,
    String name,
    byte[] image,
    String brand,
    Color color,
    MasterCategory masterCategory,
    SubCategory subCategory,
    Type type,
    Season season,
    Usage usage,
    boolean isFavorite
) {
}
