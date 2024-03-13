package de.gaknr.mspbackend.clothingitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingItemService {

    private final ClothingItemRepository repository;

    public void save(ClothingItemEntity clothingItemEntity) {
        repository.save(clothingItemEntity);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public ClothingItemEntity getById(int id) {
        Optional<ClothingItemEntity> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public List<ClothingItemEntity> getAll() {
        return repository.findAll();
    }

}
