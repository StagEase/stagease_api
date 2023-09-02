package stag.ease.stagease.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Entity.Enum.Distrito;
import stag.ease.stagease.Entity.SolicitacaoEntity;
import stag.ease.stagease.Entity.SupervisorEntity;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UBSDTO {
    @NotBlank(message = "Este campo não pode ser nullo")
    private String nomeArea;

    @NotBlank(message = "Este campo não pode ser nullo")
    private String gerente;

    @NotBlank(message = "Este campo não pode ser nullo")
    private Distrito distrito;

    private List<String> contatos;

    private List<SupervisorEntity> supervisor;

    @NotBlank(message = "Este campo não pode ser nullo")
    private List<AreaEntity> areasUBS;

    private List<SolicitacaoEntity> solicitacoes;

    private String descricao;
}
