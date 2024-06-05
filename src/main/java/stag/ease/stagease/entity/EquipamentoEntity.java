package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import stag.ease.stagease.entity.enums.Distrito;

import java.util.List;

@Getter
@Setter
@Entity
//@Audited
@Table(name = "equipamento")
//@AuditTable(value = "tb_equipamento_audit", schema = "audit")
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
            name = "supervisor_equipamento",
            joinColumns = @JoinColumn(name = "supervisor_id"),
            inverseJoinColumns = @JoinColumn(name = "equipamento_id"))
    private List<SupervisorEntity> supervisorList;

    @ManyToMany
    @JoinTable(name = "area_equipamento ", joinColumns = @JoinColumn(name = "equipamento_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<AreaEntity> areaList;

    private String descricao;
}
