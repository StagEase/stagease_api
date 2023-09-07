package stag.ease.stagease.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SupervisorDTO extends AbstractDTO{
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeSupervisor;
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String matricula;
    private SolicitacaoDTO solicitacaoList;
    private UBSDTO ubsList;
    private String descricao;
}
