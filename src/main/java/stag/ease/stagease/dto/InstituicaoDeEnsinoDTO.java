package stag.ease.stagease.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class InstituicaoDeEnsinoDTO extends AbstractDTO{
    // @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeIE;
    private List<SolicitacaoDTO> solicitacaoList;
}
