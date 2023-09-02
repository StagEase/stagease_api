package stag.ease.stagease.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stag.ease.stagease.Entity.Enum.Situacao;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoDTO extends AbstractDTO{
    @NotBlank(message = "Este campo não pode ser nullo")
    private UBSDTO ubs;
    @NotBlank(message = "Este campo não pode ser nullo")
    private AreaDTO AreaDeSolicitacao;
    @NotBlank(message = "Este campo não pode ser nullo")
    private SupervisorDTO supervisor;
    @NotBlank(message = "Este campo não pode ser nullo")
    private int qntdEstagiarios;
    @NotBlank(message = "Este campo não pode ser nullo")
    private InstituicaiDeEnsinoDTO instituicaiDeEnsino;
    @NotBlank(message = "Este campo não pode ser nullo")
    private LocalDate dataInicio;
    @NotBlank(message = "Este campo não pode ser nullo")
    private LocalDate dataFim;
    @NotBlank(message = "Este campo não pode ser nullo")
    private LocalTime inicioExpediente;
    @NotBlank(message = "Este campo não pode ser nullo")
    private LocalTime fimExpediente;
    private Situacao situacao;
    private String descricao;
}