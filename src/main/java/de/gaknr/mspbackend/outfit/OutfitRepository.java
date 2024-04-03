package de.gaknr.mspbackend.outfit;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutfitRepository extends MongoRepository<OutfitEntity, ObjectId> {
}
