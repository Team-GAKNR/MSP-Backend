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

    public void save(ClothingItemEntity clothingItemEntity) {
        repository.save(clothingItemEntity);
    }

    public void deleteById(ObjectId id) {
        repository.deleteById(id);
    }

    public ClothingItemEntity getById(ObjectId id) {
        Optional<ClothingItemEntity> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public List<ClothingItemEntity> getAll() {
        return repository.findAll();
    }

    public void deleteClothingItemFromUserOutfitsAndUser(ObjectId clothingItemId, ObjectId userId) {
        List<ObjectId> userOutfits = userService.getById(userId).getOutfits();
        for(ObjectId outfit : userOutfits) {

            OutfitEntity outfitEntity = outfitService.getById(outfit);

            for(ObjectId clothingItem : outfitEntity.getPieces()) {
                if(clothingItem.equals(clothingItemId)) {
                    outfitEntity.getPieces().remove(clothingItem);
                    outfitService.save(outfitEntity);
                }
            }
        }

        UserEntity userEntity = userService.getById(userId);
        userEntity.getCloset().remove(clothingItemId);
        userService.save(userEntity);
        deleteById(clothingItemId);

    }

}
