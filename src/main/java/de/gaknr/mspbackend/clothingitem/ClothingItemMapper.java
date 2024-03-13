package de.gaknr.mspbackend.clothingitem;

import org.springframework.stereotype.Service;

@Service
public class ClothingItemMapper {

    public ClothingItemEntity mapAddClothingItemDtoToEntity(AddClothingItemDTO addClothingItemDTO) {
        ClothingItemEntity clothingItemEntity = new ClothingItemEntity();
        clothingItemEntity.setName(addClothingItemDTO.name());
        clothingItemEntity.setImage(addClothingItemDTO.image());
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
        GetClothingItemDTO getClothingItemDTO = new GetClothingItemDTO(
            clothingItemEntity.getId(),
            clothingItemEntity.getName(),
            clothingItemEntity.getImage(),
            clothingItemEntity.getBrand(),
            clothingItemEntity.getColor(),
            clothingItemEntity.getMasterCategory(),
            clothingItemEntity.getSubCategory(),
            clothingItemEntity.getType(),
            clothingItemEntity.getSeason(),
            clothingItemEntity.getUsage(),
            clothingItemEntity.isFavorite()
        );
        return getClothingItemDTO;
    }

}
