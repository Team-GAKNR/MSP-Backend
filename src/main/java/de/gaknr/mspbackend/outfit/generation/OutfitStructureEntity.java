package de.gaknr.mspbackend.outfit.generation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Objects;


@AllArgsConstructor
@Getter
@Setter
public class OutfitStructureEntity {

    private ObjectId shoes;
    private ObjectId bottomwear;
    private ObjectId topwear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutfitStructureEntity that = (OutfitStructureEntity) o;
        return Objects.equals(shoes, that.shoes) && Objects.equals(bottomwear, that.bottomwear) && Objects.equals(topwear, that.topwear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoes, bottomwear, topwear);
    }
}
