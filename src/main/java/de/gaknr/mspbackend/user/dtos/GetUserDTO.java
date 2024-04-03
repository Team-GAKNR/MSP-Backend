package de.gaknr.mspbackend.user.dtos;

import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;
import de.gaknr.mspbackend.outfit.dtos.GetOutfitDTO;
import org.bson.types.ObjectId;

import java.util.List;

public record GetUserDTO(
    ObjectId id,
    String keycloakUserId,
    List<GetClothingItemDTO> closet,
    List<GetOutfitDTO> outfits
) {
}
