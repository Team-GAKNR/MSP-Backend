package de.gaknr.mspbackend.user;

import de.gaknr.mspbackend.clothingitem.ClothingItemMapper;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.clothingitem.dtos.GetClothingItemDTO;

import de.gaknr.mspbackend.outfit.OutfitMapper;
import de.gaknr.mspbackend.outfit.OutfitService;
import de.gaknr.mspbackend.outfit.dtos.GetOutfitDTO;

import de.gaknr.mspbackend.user.dtos.AddUserDTO;
import de.gaknr.mspbackend.user.dtos.GetUserDTO;

import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final ClothingItemService clothingItemService;
    private final ClothingItemMapper clothingItemMapper;

    private final OutfitService outfitService;
    private final OutfitMapper outfitMapper;

    public UserEntity mapUserDTOToEntity(AddUserDTO addUserDTO) {
        UserEntity entity = new UserEntity();
        entity.setKeycloakUserId(addUserDTO.keycloakUserId());
        entity.setCloset(addUserDTO.closet());
        entity.setOutfits(addUserDTO.outfits());

        return entity;
    }

    public GetUserDTO mapUserEntityToDTO(UserEntity userEntity) {
        List<GetClothingItemDTO> closetList = new ArrayList<>();
        for(ObjectId id : userEntity.getCloset()){
            closetList.add(this.clothingItemMapper.mapClothingItemEntityToGetClothingItemDTO(this.clothingItemService.getById(id)));
        }
        List<GetOutfitDTO> outfitsList = new ArrayList<>();
        for(ObjectId id : userEntity.getOutfits()){
            outfitsList.add(this.outfitMapper.mapOutfitEntityToDTO(this.outfitService.getById(id)));
        }
        return new GetUserDTO(
            userEntity.getId(),
            userEntity.getKeycloakUserId(),
            closetList,
            outfitsList
        );
    }

}
