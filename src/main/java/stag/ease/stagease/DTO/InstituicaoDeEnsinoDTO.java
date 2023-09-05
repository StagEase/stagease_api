package stag.ease.stagease.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoDeEnsinoDTO extends AbstractDTO{
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeIE;
    private SolicitacaoDTO solicitacaoDTOList;
}
