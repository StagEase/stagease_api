package stag.ease.stagease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stag.ease.stagease.entity.EquipamentoEntity;

public interface EquipamentoRepository extends JpaRepository<EquipamentoEntity, Long> {
    EquipamentoEntity findByNomeEquipamento(String nome);
    boolean existsByIdAndAtivoIsTrue(Long id);
}
