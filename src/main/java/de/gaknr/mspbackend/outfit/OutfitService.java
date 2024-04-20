package de.gaknr.mspbackend.outfit;

import de.gaknr.mspbackend.user.UserEntity;
import de.gaknr.mspbackend.user.UserService;
import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutfitService {

    private final OutfitRepository repository;
    private final UserService userService;

    public void save(OutfitEntity outfitEntity, String userId) {
        UserEntity entity = userService.getById(userId);
        entity.getOutfits().add(repository.save(outfitEntity).getId());
        userService.update(entity, userId);
    }

    public void deleteById(ObjectId id, String userId) {
        UserEntity entity = userService.getById(userId);
        if(entity.getOutfits().contains(id)) {
            entity.getOutfits().remove(id);
            userService.update(entity, userId);
            repository.deleteById(id);
        }
    }

    public OutfitEntity getById(ObjectId id) {
        Optional<OutfitEntity> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public void update(OutfitEntity outfitEntity, ObjectId id) {
        Optional<OutfitEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            optional.get().setPieces(outfitEntity.getPieces());
            optional.get().setFavorite(outfitEntity.isFavorite());
            repository.save(optional.get());
        }
    }

}
