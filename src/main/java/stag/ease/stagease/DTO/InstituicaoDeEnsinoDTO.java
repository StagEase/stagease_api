package stag.ease.stagease.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaiDeEnsinoDTO {
    private Long id;
    private boolean ativo;
    //    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeIE;
    private SolicitacaoDTO solicitacaoDTOList;
}
