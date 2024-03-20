package de.gaknr.mspbackend.outfit;

import de.gaknr.mspbackend.clothingitem.ClothingItemEntity;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.outfit.dtos.AddOutfitDTO;
import de.gaknr.mspbackend.outfit.dtos.GetOutfitDTO;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OutfitMapper {

    private final ClothingItemService clothingItemService;

    public OutfitEntity mapOutfitDTOToEntity(AddOutfitDTO addOutfitDTO) {
        OutfitEntity entity = new OutfitEntity();
        entity.setPieces(addOutfitDTO.pieces());
        entity.setFavorite(addOutfitDTO.isFavorite());

        return entity;
    }

    public GetOutfitDTO mapOutfitEntityToDTO(OutfitEntity outfitEntity) {
        List<ClothingItemEntity> list = new ArrayList<>();
        for(ObjectId id : outfitEntity.getPieces()){
            list.add(this.clothingItemService.getById(id));
        }
        return new GetOutfitDTO(
            outfitEntity.getId(),
            list,
            outfitEntity.isFavorite()
        );
    }

}
