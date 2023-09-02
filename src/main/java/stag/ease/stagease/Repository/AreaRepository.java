package stag.ease.stagease.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stag.ease.stagease.Entity.AreaEntity;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
    AreaEntity findByNomeArea(String nome);
}
