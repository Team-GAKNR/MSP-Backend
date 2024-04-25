package de.gaknr.mspbackend.user;

import de.gaknr.mspbackend.clothingitem.ClothingItemRepository;
import de.gaknr.mspbackend.clothingitem.ClothingItemService;
import de.gaknr.mspbackend.outfit.OutfitRepository;
import de.gaknr.mspbackend.outfit.OutfitService;
import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

//    private final OutfitService outfitService;
//
//    private final ClothingItemService clothingItemService;

    private final OutfitRepository outfitRepository;
    private final ClothingItemRepository clothingItemRepository;

    public void save(UserEntity userEntity) {
        this.repository.save(userEntity);
    }

    public void deleteById(String id) {
        OutfitService outfitService = new OutfitService(outfitRepository, repository, clothingItemRepository);
        ClothingItemService clothingItemService = new ClothingItemService(clothingItemRepository, repository, outfitRepository);
        UserEntity entity = getById(id);
        for(ObjectId outfitId : entity.getOutfits()) {
            outfitService.deleteById(outfitId, id);
        }
        for(ObjectId clothingItemId : entity.getCloset()) {
            clothingItemService.deleteById(clothingItemId, id);
        }
        this.repository.deleteById(entity.getId());
    }

    public List<UserEntity> getAll() {
        return this.repository.findAll();
    }

    public UserEntity getById(String id) {
        Optional<UserEntity> optional = this.repository.findByKeycloakUserId(id);
        return optional.orElse(null);
    }

    public void update(UserEntity userEntityNew, String id) {
        Optional<UserEntity> optional = this.repository.findByKeycloakUserId(id);
        if(optional.isPresent()){
            userEntityNew.setId(optional.get().getId());
            this.repository.save(userEntityNew);
        }
    }

}
