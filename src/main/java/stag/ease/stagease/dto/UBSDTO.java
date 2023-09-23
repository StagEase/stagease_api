package stag.ease.stagease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.enums.Distrito;
import stag.ease.stagease.entity.SolicitacaoEntity;
import stag.ease.stagease.entity.SupervisorEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UBSDTO extends AbstractDTO {
    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeUBS;

    @NotBlank(message = "Este campo não pode ser nulo")
    private String gerente;

    @NotNull(message = "Este campo não pode ser nulo")
    private Distrito distrito;

    private List<String> contatoList;

    private List<SupervisorEntity> supervisorList;

    @NotNull(message = "Este campo não pode ser nulo")
    private List<AreaEntity> areaList;

    private List<SolicitacaoEntity> solicitacoes;

    private String descricao;
}
