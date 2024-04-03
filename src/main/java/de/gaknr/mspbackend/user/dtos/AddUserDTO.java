package de.gaknr.mspbackend.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.util.List;

public record AddUserDTO(
    @NotNull @NotBlank String keycloakUserId,
    List<ObjectId> closet,
    List<ObjectId> outfits
) {
}
