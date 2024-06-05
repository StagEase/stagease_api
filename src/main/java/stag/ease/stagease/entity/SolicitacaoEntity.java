package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import stag.ease.stagease.entity.enums.Situacao;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
//@Audited
@Table(name = "solicitacao")
//@AuditTable(value = "tb_solicitacao_audit", schema = "audit")
public class SolicitacaoEntity extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_id", referencedColumnName = "id", nullable = false)
    private EquipamentoEntity equipamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private AreaEntity area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id", nullable = false)
    private SupervisorEntity supervisor;

    @Column(nullable = false)
    private int qntdEstagiarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicaoDeEnsino_id", referencedColumnName = "id", nullable = false)
    private InstituicaoDeEnsinoEntity instituicaoDeEnsino;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    private LocalTime inicioExpediente;

    @Column(nullable = false)
    private LocalTime fimExpediente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Situacao situacao;

    private String descricao;
}
