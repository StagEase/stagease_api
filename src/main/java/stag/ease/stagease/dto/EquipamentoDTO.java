package stag.ease.stagease.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.entity.enums.Distrito;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoDTO extends AbstractDTO {
    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeEquipamento;
    @NotBlank(message = "Este campo não pode ser nulo")
    private String gerente;
    @NotNull(message = "Este campo não pode ser nulo")
    private Distrito distrito;
    private List<String> contatoList;
    private List<SupervisorDTO> supervisorList;
    @NotNull(message = "Este campo não pode ser nulo")
    private List<AreaDTO> areaList;
    private String descricao;
}
