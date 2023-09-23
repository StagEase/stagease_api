package stag.ease.stagease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stag.ease.stagease.entity.UBSEntity;

public interface UBSRepository extends JpaRepository<UBSEntity, Long> {
    UBSEntity findByNomeUBS(String nome);
    boolean existsByIdAndAtivoIsTrue(Long id);
}
