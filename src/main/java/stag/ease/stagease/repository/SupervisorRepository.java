package stag.ease.stagease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stag.ease.stagease.entity.SupervisorEntity;

@Repository
public interface SupervisorRepository extends JpaRepository<SupervisorEntity, Long> {
    SupervisorEntity findByNomeSupervisor(String nome);
    SupervisorEntity findByMatricula(String matricula);
}
