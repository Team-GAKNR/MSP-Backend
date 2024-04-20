package de.gaknr.mspbackend.user;

import lombok.RequiredArgsConstructor;

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

    public void deleteById(String id) {
        UserEntity entity = getById(id);
        this.repository.deleteById(entity.getId());
    }

    public List<UserEntity> getAll() {
        return this.repository.findAll();
    }

    public UserEntity getById(String id) {
        Optional<UserEntity> optional = this.repository.findByKeycloakUserId(id);
        return optional.orElse(null);
    }

    public void update(UserEntity userEntityNew, String id) {
        Optional<UserEntity> optional = this.repository.findByKeycloakUserId(id);
        if(optional.isPresent()){
            optional.get().setCloset(userEntityNew.getCloset());
            optional.get().setOutfits(userEntityNew.getOutfits());
            this.repository.save(optional.get());
        }
    }

}
