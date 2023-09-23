package stag.ease.stagease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stag.ease.stagease.entity.AreaEntity;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
    AreaEntity findByNomeArea(String nome);
}
