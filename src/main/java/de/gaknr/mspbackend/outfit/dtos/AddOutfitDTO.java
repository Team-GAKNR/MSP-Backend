package de.gaknr.mspbackend.outfit.dtos;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.List;

public record AddOutfitDTO(
    List<ObjectId> pieces,
    @NotNull boolean isFavorite
) {
}
