package de.gaknr.mspbackend.outfit;

import de.gaknr.mspbackend.clothingitem.ClothingItemMapper;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;

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
    private final ClothingItemMapper clothingItemMapper;

    public OutfitEntity mapOutfitDTOToEntity(AddOutfitDTO addOutfitDTO) {
        OutfitEntity entity = new OutfitEntity();
        entity.setPieces(addOutfitDTO.pieces());
        entity.setFavorite(addOutfitDTO.isFavorite());

        return entity;
    }

    public GetOutfitDTO mapOutfitEntityToDTO(OutfitEntity outfitEntity) {
        List<GetClothingItemDTO> list = new ArrayList<>();
        for (ObjectId id : outfitEntity.getPieces()) {
            list.add(this.clothingItemMapper.mapClothingItemEntityToGetClothingItemDTO(this.clothingItemService.getById(id)));
        }
        return new GetOutfitDTO(
            outfitEntity.getId(),
            list,
            outfitEntity.isFavorite()
        );
    }

}
