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
public class InstituicaoDeEnsinoDTO extends AbstractDTO {
    @NotBlank(message = "Este campo não pode ser nulo")
    private String nome;
    @JsonIgnoreProperties({"ubs", "area", "supervisor", "instituicaoDeEnsino"})
    private List<SolicitacaoDTO> solicitacaoList;
}
