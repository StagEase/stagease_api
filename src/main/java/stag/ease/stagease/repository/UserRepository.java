package stag.ease.stagease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stag.ease.stagease.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
