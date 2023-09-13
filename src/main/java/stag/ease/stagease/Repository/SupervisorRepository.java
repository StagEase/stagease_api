package stag.ease.stagease.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stag.ease.stagease.Entity.SupervisorEntity;

@Repository
public interface SupervisorRepository extends JpaRepository<SupervisorEntity, Long> {
    SupervisorEntity findByNomeSupervisor(String nome);
}
