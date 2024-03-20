package de.gaknr.mspbackend.user;

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
@Document(collection = "User")
public class UserEntity {

    @MongoId
    private ObjectId id;

    @Field
    private String keycloakUserId;

    @Field
    private List<ObjectId> closet;

    @Field
    private List<ObjectId> outfits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(keycloakUserId, that.keycloakUserId) && Objects.equals(closet, that.closet) && Objects.equals(outfits, that.outfits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keycloakUserId, closet, outfits);
    }

}
