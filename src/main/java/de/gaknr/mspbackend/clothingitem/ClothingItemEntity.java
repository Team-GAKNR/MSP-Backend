package de.gaknr.mspbackend.clothingitem;

import de.gaknr.mspbackend.clothingitem.enums.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.types.ObjectId;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "ClothingItem")
public class ClothingItemEntity {

    @MongoId
    private ObjectId id;

    @Field
    private String name;

    @Field
    private String image;

    @Field
    private String brand;

    @Field
    private Color color;

    @Field
    private MasterCategory masterCategory;

    @Field
    private SubCategory subCategory;

    @Field
    private Type type;

    @Field
    private Season season;

    @Field
    private Usage usage;

    @Field
    private boolean isFavorite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothingItemEntity that = (ClothingItemEntity) o;
        return isFavorite == that.isFavorite && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(image, that.image) && Objects.equals(brand, that.brand) && Objects.equals(color, that.color) && masterCategory == that.masterCategory && subCategory == that.subCategory && Objects.equals(type, that.type) && Objects.equals(season, that.season) && Objects.equals(usage, that.usage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, brand, color, masterCategory, subCategory, type, season, usage, isFavorite);
    }

}
