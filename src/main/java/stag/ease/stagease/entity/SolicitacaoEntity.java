package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "solicitacao", schema = "stagease")
public class SolicitacaoEntity extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubs_id", referencedColumnName = "id", nullable = false)
    private UBSEntity ubs;

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


    @Column
    private String descricao;
}
