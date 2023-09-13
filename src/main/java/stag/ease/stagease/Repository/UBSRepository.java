package stag.ease.stagease.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Entity.UBSEntity;

public interface UBSRepository extends JpaRepository<UBSEntity, Long> {
    UBSEntity findByNomeUBS(String nome);
    boolean existsByIdAndAtivoIsTrue(Long id);
}
