package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.List;

@Getter
@Setter
@Entity
@Audited
@Table(name = "area", schema = "stagease")
@AuditTable(value = "tb_area_audit", schema = "audit")
public class AreaEntity extends AbstractEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String nomeArea;

    @ManyToMany(mappedBy = "areaList")
    private List<EquipamentoEntity> equipamentoList;

    @OneToMany(mappedBy = "area")
    private List<SolicitacaoEntity> solicitacaoList;
}
