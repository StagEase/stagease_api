package stag.ease.stagease.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.entity.SolicitacaoEntity;
import stag.ease.stagease.entity.UBSEntity;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AreaDTO extends AbstractDTO{
    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeArea;

    private List<UBSEntity> ubsList;

    private List<SolicitacaoEntity> solicitacaoList;
}
