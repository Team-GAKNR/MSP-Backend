package de.gaknr.mspbackend.outfit;

import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutfitService {

    private final OutfitRepository repository;

    public void save(OutfitEntity outfitEntity) {
        repository.save(outfitEntity);
    }

    public void deleteById(ObjectId id) {
        repository.deleteById(id);
    }

    public OutfitEntity getById(ObjectId id) {
        Optional<OutfitEntity> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public List<OutfitEntity> getAll() {
        return repository.findAll();
    }

}
