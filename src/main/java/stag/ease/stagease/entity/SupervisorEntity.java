package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "supervisor", schema = "stagease")
public class SupervisorEntity extends AbstractEntity {

    @Column(length = 60, nullable = false)
    private String nomeSupervisor;

    @Column(length = 40, nullable = false, unique = true)
    private String matricula;

    @OneToMany(mappedBy = "supervisor")
    private List<SolicitacaoEntity> solicitacaoList;

    @ManyToMany(mappedBy = "supervisorList")
    private List<EquipamentoEntity> equipamentoList;

    private String descricao;
}
