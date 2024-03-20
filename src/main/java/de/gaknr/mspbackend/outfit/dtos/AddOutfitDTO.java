package de.gaknr.mspbackend.outfit.dtos;

import de.gaknr.mspbackend.clothingitem.ClothingItemEntity;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddOutfitDTO(
    List<ClothingItemEntity> pieces,
    @NotNull boolean isFavorite
) {
}
