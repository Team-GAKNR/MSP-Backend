package de.gaknr.mspbackend.outfit.dtos;

import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;

import java.util.List;

public record GetOutfitDTO(
    String _id,
    List<GetClothingItemDTO> pieces,
    boolean isFavorite
) {
}
