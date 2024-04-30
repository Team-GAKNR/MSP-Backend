package de.gaknr.mspbackend.outfit.generation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;


@AllArgsConstructor
@Getter
@Setter
public class OutfitStructureEntity {

    private ObjectId shoes;
    private ObjectId bottomwear;
    private ObjectId topwear;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OutfitStructureEntity other = (OutfitStructureEntity) obj;
        return shoes.equals(other.shoes) && bottomwear.equals(other.bottomwear) && topwear.equals(other.topwear);
    }

    @Override
    public int hashCode() {
        int result = shoes.hashCode();
        result = 31 * result + bottomwear.hashCode();
        result = 31 * result + topwear.hashCode();
        return result;
    }
}
