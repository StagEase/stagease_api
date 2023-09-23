package stag.ease.stagease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stag.ease.stagease.entity.SolicitacaoEntity;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoEntity, Long> {
}
