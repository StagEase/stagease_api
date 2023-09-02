package stag.ease.stagease.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import stag.ease.stagease.Entity.Enum.Situacao;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "solicitacao", schema = "stagease")
public class SolicitacaoEntity extends AbstractEntity {
    @Column(nullable = false)
    private UBSEntity ubs;

    @Column(nullable = false)
    private AreaEntity areaSolicitacao;

    @Column(nullable = false)
    private SupervisorEntity supervisor;

    @Column(nullable = false)
    private int qntdEstagiarios;

    @Column(nullable = false)
    private InstituicaiDeEnsinoEntity instituicaiDeEnsino;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    private LocalTime inicioExpediente;

    @Column(nullable = false)
    private LocalTime fimExpediente;

    @Column(nullable = false)
    private Situacao situacao;

    @Column
    private String descricao;
}
