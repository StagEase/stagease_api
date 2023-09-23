package stag.ease.stagease.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotNull(message = "Este campo não pode ser nulo")
    private UBSDTO ubs;
    @NotNull(message = "Este campo não pode ser nulo")
    private AreaDTO area;
    @NotNull(message = "Este campo não pode ser nulo")
    private SupervisorDTO supervisor;
    @Positive(message = "O numero deve ser positivo")
    @NotNull(message = "Este campo não pode ser nulo")
    private int qntdEstagiarios;
    @NotNull(message = "Este campo não pode ser nulo")
    private InstituicaoDeEnsinoDTO instituicaoDeEnsino;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalDate dataInicio;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalDate dataFim;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalTime inicioExpediente;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalTime fimExpediente;
    private Situacao situacao;
    private String descricao;
}