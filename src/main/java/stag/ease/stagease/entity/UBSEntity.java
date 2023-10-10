package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stag.ease.stagease.entity.enums.Distrito;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ubs", schema = "stagease")
public class UBSEntity extends AbstractEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String nomeUBS;

    @Column(length = 50, nullable = false)
    private String gerente;

    @Enumerated(EnumType.STRING)
    @Column()
    private Distrito distrito;

    @Column
    private List<String> contatoList;

    @ManyToMany
    @JoinTable(
            name = "ubs-supervisor",
            schema = "stagease",
            joinColumns = @JoinColumn(name = "supervisor_id"),
            inverseJoinColumns = @JoinColumn(name = "ubs_id"))
    private List<SupervisorEntity> supervisorList;

    @ManyToMany
    @JoinTable(name = "ubs_area", schema = "stagease", joinColumns = @JoinColumn(name = "ubs_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<AreaEntity> areaList;

    @OneToMany(mappedBy = "ubs")
    private List<SolicitacaoEntity> solicitacaoList;

    @Column
    private String descricao;
}
