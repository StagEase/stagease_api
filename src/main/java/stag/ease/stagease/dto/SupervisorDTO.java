package stag.ease.stagease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SupervisorDTO extends AbstractDTO{
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeSupervisor;
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String matricula;
    private List<SolicitacaoDTO> solicitacaoList;
    private List<UBSDTO> ubsList;
    private String descricao;
}
