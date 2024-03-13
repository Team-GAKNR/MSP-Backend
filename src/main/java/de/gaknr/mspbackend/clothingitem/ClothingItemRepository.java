package de.gaknr.mspbackend.clothingitem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingItemRepository extends MongoRepository<ClothingItemEntity, Integer> {
}
