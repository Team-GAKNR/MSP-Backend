package de.gaknr.mspbackend.user;

import lombok.RequiredArgsConstructor;

import org.bson.types.ObjectId;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void save(UserEntity userEntity) {
        this.repository.save(userEntity);
    }

    public void deleteById(ObjectId id){
        this.repository.deleteById(id);
    }

    public List<UserEntity> getAll() {
        return this.repository.findAll();
    }

    public UserEntity getById(ObjectId id) {
        Optional<UserEntity> optional = this.repository.findById(id);
        return optional.orElse(null);
    }

}
