package stag.ease.stagease.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stag.ease.stagease.Entity.SolicitacaoEntity;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoEntity, Long> {
}
