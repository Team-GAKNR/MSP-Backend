package de.gaknr.mspbackend.clothingitem;

import de.gaknr.mspbackend.clothingitem.enums.*;
import jakarta.validation.constraints.NotNull;

public record AddClothingItemDTO(
    String name,
    @NotNull byte[] image,
    String brand,
    @NotNull Color color,
    @NotNull MasterCategory masterCategory,
    @NotNull SubCategory subCategory,
    @NotNull Type type,
    @NotNull Season season,
    @NotNull Usage usage,
    @NotNull boolean isFavorite
) {
}
