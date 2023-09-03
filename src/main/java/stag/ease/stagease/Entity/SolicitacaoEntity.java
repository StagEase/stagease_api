package stag.ease.stagease.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.Entity.Enum.Situacao;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "solicitacao", schema = "stagease")
public class SolicitacaoEntity extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "ubs_id", referencedColumnName = "id", nullable = false)
    private UBSEntity ubs;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private AreaEntity area;

    @ManyToOne
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id", nullable = false)
    private SupervisorEntity supervisor;

    @Column(nullable = false)
    private int qntdEstagiarios;

    @ManyToOne
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

    @Column(nullable = false)
    private Situacao situacao;

    @Column
    private String descricao;
}
