package de.gaknr.mspbackend.outfit;

import de.gaknr.mspbackend.outfit.dtos.AddOutfitDTO;
import de.gaknr.mspbackend.outfit.dtos.GetOutfitDTO;

import org.springframework.stereotype.Service;

@Service
public class OutfitMapper {

    public OutfitEntity mapOutfitDTOToEntity(AddOutfitDTO addOutfitDTO) {
        OutfitEntity entity = new OutfitEntity();
        entity.setPieces(addOutfitDTO.pieces());
        entity.setFavorite(addOutfitDTO.isFavorite());

        return entity;
    }

    public GetOutfitDTO mapOutfitEntityToDTO(OutfitEntity outfitEntity) {
        return new GetOutfitDTO(
            outfitEntity.getId(),
            outfitEntity.getPieces(),
            outfitEntity.isFavorite()
        );
    }

}
