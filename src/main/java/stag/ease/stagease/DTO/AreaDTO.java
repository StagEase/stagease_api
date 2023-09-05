package stag.ease.stagease.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.Entity.SolicitacaoEntity;
import stag.ease.stagease.Entity.UBSEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AreaDTO {
    private Long id;

    private boolean ativo;

    @NotBlank(message = "Este campo não pode ser nulo")
    private String nomeArea;

    private List<UBSEntity> ubsList;

    private List<SolicitacaoEntity> solicitacaoList;
}
