package stag.ease.stagease.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.entity.enums.Situacao;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoDTO extends AbstractDTO {
    @JsonIgnoreProperties("solicitacaoList")
    @NotNull(message = "Este campo não pode ser nulo")
    private UBSDTO ubs;
    @JsonIgnoreProperties("solicitacaoList")
    @NotNull(message = "Este campo não pode ser nulo")
    private AreaDTO area;
    @JsonIgnoreProperties("solicitacaoList")
    @NotNull(message = "Este campo não pode ser nulo")
    private SupervisorDTO supervisor;
    @Positive(message = "O numero deve ser positivo")
    private int qntdEstagiarios;
    @JsonIgnoreProperties("solicitacaoList")
    @NotNull(message = "Este campo não pode ser nulo")
    private InstituicaoDeEnsinoDTO instituicaoDeEnsino;
    @NotNull(message = "Este campo não pode ser nulo")
    private LocalDate dataInicio;
    @NotNull(message = "Este campo não pode ser nulo")
    private LocalDate dataFim;
    @NotNull(message = "Este campo não pode ser nulo")
    private LocalTime inicioExpediente;
    @NotNull(message = "Este campo não pode ser nulo")
    private LocalTime fimExpediente;
    private Situacao situacao;
    private String descricao;
}