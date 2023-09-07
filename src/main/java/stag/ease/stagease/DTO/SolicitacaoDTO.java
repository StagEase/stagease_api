package stag.ease.stagease.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.Entity.Enum.Situacao;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SolicitacaoDTO extends AbstractDTO{
//    @NotBlank(message = "Este campo não pode ser nulo")
    private UBSDTO ubs;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private AreaDTO AreaDeSolicitacao;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private SupervisorDTO supervisor;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private int qntdEstagiarios;
//    @NotBlank(message = "Este campo não pode ser nulo")
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