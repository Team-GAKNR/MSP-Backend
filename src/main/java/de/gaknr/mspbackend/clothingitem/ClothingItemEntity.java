package de.gaknr.mspbackend.clothingitem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "Clothing_Item")
public class ClothingItemEntity {

    @Id
    private int id;

    private String name;

    private Base64 image;

    private String brand;

    private String color;

    private MasterCategory masterCategory;

    private SubCategory subCategory;

    private String type;

    private String season;

    private String usage;

    private boolean isFavorite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothingItemEntity that = (ClothingItemEntity) o;
        return id == that.id && isFavorite == that.isFavorite && Objects.equals(name, that.name) && Objects.equals(image, that.image) && Objects.equals(brand, that.brand) && Objects.equals(color, that.color) && masterCategory == that.masterCategory && subCategory == that.subCategory && Objects.equals(type, that.type) && Objects.equals(season, that.season) && Objects.equals(usage, that.usage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, brand, color, masterCategory, subCategory, type, season, usage, isFavorite);
    }
    }
