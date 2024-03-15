package de.gaknr.mspbackend.clothingitem;

import de.gaknr.mspbackend.clothingitem.dtos.AddClothingItemDTO;
import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ClothingItemMapper {

    public ClothingItemEntity mapAddClothingItemDtoToEntity(AddClothingItemDTO addClothingItemDTO) {
        ClothingItemEntity clothingItemEntity = new ClothingItemEntity();
        clothingItemEntity.setName(addClothingItemDTO.name());
        clothingItemEntity.setImage(Base64.getEncoder().encodeToString(addClothingItemDTO.image()));
        clothingItemEntity.setBrand(addClothingItemDTO.brand());
        clothingItemEntity.setColor(addClothingItemDTO.color());
        clothingItemEntity.setMasterCategory(addClothingItemDTO.masterCategory());
        clothingItemEntity.setSubCategory(addClothingItemDTO.subCategory());
        clothingItemEntity.setType(addClothingItemDTO.type());
        clothingItemEntity.setSeason(addClothingItemDTO.season());
        clothingItemEntity.setUsage(addClothingItemDTO.usage());
        clothingItemEntity.setFavorite(addClothingItemDTO.isFavorite());

        return clothingItemEntity;
    }

    public GetClothingItemDTO mapClothingItemEntityToGetClothingItemDTO(ClothingItemEntity clothingItemEntity){
        return new GetClothingItemDTO(
            clothingItemEntity.getId(),
            clothingItemEntity.getName(),
            Base64.getDecoder().decode(clothingItemEntity.getImage()),
            clothingItemEntity.getBrand(),
            clothingItemEntity.getColor(),
            clothingItemEntity.getMasterCategory(),
            clothingItemEntity.getSubCategory(),
            clothingItemEntity.getType(),
            clothingItemEntity.getSeason(),
            clothingItemEntity.getUsage(),
            clothingItemEntity.isFavorite()
        );
    }

}
