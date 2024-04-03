package de.gaknr.mspbackend.outfit.dtos;

import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;
import org.bson.types.ObjectId;

import java.util.List;

public record GetOutfitDTO(
    ObjectId id,
    List<GetClothingItemDTO> pieces,
    boolean isFavorite
) {
}
