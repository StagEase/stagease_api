package stag.ease.stagease.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {
    private Long id;
    private boolean ativo;
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeSupervisor;
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String matricula;
    private SolicitacaoDTO solicitacaoList;
    private UBSDTO ubsList;
    private String descricao;
}
