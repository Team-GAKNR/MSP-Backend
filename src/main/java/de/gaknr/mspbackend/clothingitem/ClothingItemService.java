package de.gaknr.mspbackend.clothingitem;

import de.gaknr.mspbackend.outfit.OutfitEntity;
import de.gaknr.mspbackend.outfit.OutfitService;

import de.gaknr.mspbackend.user.UserEntity;
import de.gaknr.mspbackend.user.UserService;

import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingItemService {

    private final ClothingItemRepository repository;
    private final UserService userService;
    private final OutfitService outfitService;

    public void save(ClothingItemEntity clothingItemEntity, String userId) {
        UserEntity entity = userService.getById(userId);
        entity.getCloset().add(repository.save(clothingItemEntity).getId());
        userService.update(entity, userId);
    }

    public void update(ClothingItemEntity clothingItemEntity, ObjectId id) {
        Optional<ClothingItemEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            clothingItemEntity.setId(id);
            repository.save(clothingItemEntity);
        }
    }

    public ClothingItemEntity getById(ObjectId id) {
        Optional<ClothingItemEntity> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public void deleteClothingItemFromUserById(ObjectId clothingItemId, String userId) {
        List<ObjectId> userOutfits = userService.getById(userId).getOutfits();
        for(ObjectId outfit : userOutfits) {

            OutfitEntity outfitEntity = outfitService.getById(outfit);

            for(ObjectId clothingItem : outfitEntity.getPieces()) {
                if(clothingItem.equals(clothingItemId)) {
                    outfitEntity.getPieces().remove(clothingItem);
                    outfitService.save(outfitEntity, userId);
                }
            }
        }

        UserEntity userEntity = userService.getById(userId);
        userEntity.getCloset().remove(clothingItemId);
        userService.update(userEntity, userId);
        repository.deleteById(clothingItemId);

    }

}
