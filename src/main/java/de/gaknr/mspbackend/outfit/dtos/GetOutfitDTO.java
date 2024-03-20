package de.gaknr.mspbackend.outfit.dtos;

import de.gaknr.mspbackend.clothingitem.ClothingItemEntity;

import org.bson.types.ObjectId;

import java.util.List;

public record GetOutfitDTO(
    ObjectId id,
    List<ClothingItemEntity> pieces,
    boolean isFavorite
) {
}
