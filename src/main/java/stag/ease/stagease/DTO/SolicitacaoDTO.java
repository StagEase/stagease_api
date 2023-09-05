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
public class SolicitacaoDTO {
    private Long id;
    private boolean ativo;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private UBSDTO ubs;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private AreaDTO AreaDeSolicitacao;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private SupervisorDTO supervisor;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private int qntdEstagiarios;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private InstituicaiDeEnsinoDTO instituicaiDeEnsino;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalDate dataInicio;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalDate dataFim;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalTime inicioExpediente;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private LocalTime fimExpediente;
//    @NotBlank(message = "Este campo não pode ser nulo")
    private Situacao situacao;
    private String descricao;

}