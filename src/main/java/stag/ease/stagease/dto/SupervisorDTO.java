package stag.ease.stagease.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorDTO extends AbstractDTO {
    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeSupervisor;
    @NotBlank(message = "Este campo não pode ser nulo")
    private String matricula;
    private String descricao;
    @JsonIgnoreProperties("supervisorList")
    private List<UBSDTO> ubsList;
    @JsonIgnoreProperties({"ubs", "area", "supervisor", "instituicaoDeEnsino"})
    private List<SolicitacaoDTO> solicitacaoList;
}
