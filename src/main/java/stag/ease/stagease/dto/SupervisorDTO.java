package stag.ease.stagease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
