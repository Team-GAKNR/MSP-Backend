package de.gaknr.mspbackend.clothingitem;

import jakarta.validation.constraints.NotNull;

public record AddClothingItemDTO(
    String name,
    @NotNull byte[] image,
    String brand,
    @NotNull String color,
    @NotNull MasterCategory masterCategory,
    @NotNull SubCategory subCategory,
    @NotNull String type,
    @NotNull String season,
    @NotNull String usage,
    @NotNull boolean isFavorite
) {
}
