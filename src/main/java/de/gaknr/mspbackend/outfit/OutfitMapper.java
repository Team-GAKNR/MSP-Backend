package de.gaknr.mspbackend.outfit;

import de.gaknr.mspbackend.clothingitem.ClothingItemMapper;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;

import de.gaknr.mspbackend.outfit.dtos.AddOutfitDTO;
import de.gaknr.mspbackend.outfit.dtos.GetOutfitDTO;

import de.gaknr.mspbackend.outfit.generation.OutfitStructureEntity;
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
            outfitEntity.getId().toString(),
            list,
            outfitEntity.isFavorite()
        );
    }

    public GetOutfitDTO mapNullOutfitEntityToDTO(OutfitEntity outfitEntity) {
        List<GetClothingItemDTO> list = new ArrayList<>();
        for (ObjectId id : outfitEntity.getPieces()) {
            list.add(this.clothingItemMapper.mapClothingItemEntityToGetClothingItemDTO(this.clothingItemService.getById(id)));
        }
        return new GetOutfitDTO(
            null,
            list,
            outfitEntity.isFavorite()
        );
    }

    public OutfitEntity mapOutfitStructureEntityToOutfitEntity(OutfitStructureEntity outfitStructureEntity) {
        List<ObjectId> piecesFromOutfitStructure = new ArrayList<>();
        piecesFromOutfitStructure.add(outfitStructureEntity.getShoes());
        piecesFromOutfitStructure.add(outfitStructureEntity.getBottomwear());
        piecesFromOutfitStructure.add(outfitStructureEntity.getTopwear());

        OutfitEntity outfitEntity = new OutfitEntity();
        outfitEntity.setPieces(piecesFromOutfitStructure);

        return outfitEntity;
    }

}
