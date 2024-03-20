package de.gaknr.mspbackend.outfit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Outfit")
public class OutfitEntity {

    @MongoId
    private ObjectId id;

    @Field
    private List<ObjectId> pieces;

    @Field
    private boolean isFavorite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutfitEntity that = (OutfitEntity) o;
        return isFavorite == that.isFavorite && Objects.equals(id, that.id) && Objects.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pieces, isFavorite);
    }

}
