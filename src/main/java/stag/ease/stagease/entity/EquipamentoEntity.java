package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stag.ease.stagease.entity.enums.Distrito;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "equipamento", schema = "stagease")
public class EquipamentoEntity extends AbstractEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String nomeEquipamento;

    @Column(length = 50, nullable = false)
    private String gerente;

    @Enumerated(EnumType.STRING)
    private Distrito distrito;

    private List<String> contatoList;

    @ManyToMany
    @JoinTable(
            name = "equipamento-supervisor",
            schema = "stagease",
            joinColumns = @JoinColumn(name = "supervisor_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
    private List<SupervisorEntity> supervisorList;

    @ManyToMany
    @JoinTable(name = "equipamento_area", schema = "stagease", joinColumns = @JoinColumn(name = "equipamento_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<AreaEntity> areaList;

    @OneToMany(mappedBy = "equipamento")
    private List<SolicitacaoEntity> solicitacaoList;

    private String descricao;
}
