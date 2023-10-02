package stag.ease.stagease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UBSDTO extends AbstractDTO {
    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeUBS;
    @NotBlank(message = "Este campo não pode ser nulo")
    private String gerente;

    private List<String> contatoList;
    @NotNull(message = "Este campo não pode ser nulo")
    private List<AreaDTO> areaList;
    private String descricao;
}
