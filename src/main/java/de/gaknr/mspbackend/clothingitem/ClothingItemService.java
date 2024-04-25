package de.gaknr.mspbackend.clothingitem;

import de.gaknr.mspbackend.outfit.OutfitEntity;
import de.gaknr.mspbackend.outfit.OutfitRepository;
import de.gaknr.mspbackend.outfit.OutfitService;

import de.gaknr.mspbackend.user.UserEntity;
import de.gaknr.mspbackend.user.UserRepository;
import de.gaknr.mspbackend.user.UserService;

import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingItemService {

    private final ClothingItemRepository repository;
    private final UserRepository userRepository;
    private final OutfitRepository outfitRepository;

    public void save(ClothingItemEntity clothingItemEntity, String userId) {
        UserService userService = new UserService(userRepository, outfitRepository, repository);
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

    public void deleteById(ObjectId clothingItemId, String userId) {
        UserService userService = new UserService(userRepository, outfitRepository, repository);
        OutfitService outfitService = new OutfitService(outfitRepository, userRepository, repository);
        UserEntity userEntity = userService.getById(userId);

        for(ObjectId outfitId : userEntity.getOutfits()) {
            OutfitEntity outfitEntity = outfitService.getById(outfitId);
            if(outfitEntity.getPieces().contains(clothingItemId)) {
                outfitEntity.getPieces().remove(clothingItemId);
                outfitService.update(outfitEntity, outfitId);
            }
        }

        if(userEntity.getCloset().contains(clothingItemId)) {
            userEntity.getCloset().remove(clothingItemId);
            userService.update(userEntity, userId);
        }

        this.repository.deleteById(clothingItemId);
    }

}
