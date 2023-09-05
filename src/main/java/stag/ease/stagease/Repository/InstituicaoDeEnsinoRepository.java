package stag.ease.stagease.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stag.ease.stagease.Entity.InstituicaoDeEnsinoEntity;

@Repository
public interface InstituicaoDeEnsinoRepository extends JpaRepository<InstituicaoDeEnsinoEntity, Long> {
}
