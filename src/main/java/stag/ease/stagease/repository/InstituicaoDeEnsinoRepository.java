package stag.ease.stagease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stag.ease.stagease.entity.InstituicaoDeEnsinoEntity;

@Repository
public interface InstituicaoDeEnsinoRepository extends JpaRepository<InstituicaoDeEnsinoEntity, Long> {
}
