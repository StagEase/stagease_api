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
public class UBSDTO extends AbstractDTO {
    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeUBS;
    @NotBlank(message = "Este campo não pode ser nulo")
    private String gerente;
    @NotNull(message = "Este campo não pode ser nulo")
    private Distrito distrito;
    private List<String> contatoList;
    @JsonIgnoreProperties("solicitacaoList")
    @NotNull(message = "Este campo não pode ser nulo")
    private List<AreaDTO> areaList;
    @JsonIgnoreProperties({"ubsList", "solicitacaoList"})
    private List<SupervisorDTO> supervisorList;
    @JsonIgnoreProperties({"ubs", "area", "supervisor", "instituicaoDeEnsino"})
    private List<SolicitacaoDTO> solicitacaoList;
    private String descricao;
}
