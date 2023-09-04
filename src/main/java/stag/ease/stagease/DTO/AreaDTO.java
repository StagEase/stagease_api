package stag.ease.stagease.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stag.ease.stagease.Entity.SolicitacaoEntity;
import stag.ease.stagease.Entity.UBSEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class AreaDTO{
   // @NotBlank(message = "Este campo não pode ser nullo")
    private Long id;
    private LocalDateTime cadastro;
    private LocalDateTime atualizacao;
    private boolean ativo;
    private String nomeArea;
//
//    private List<UBSEntity> ubsList;
//
//    private List<SolicitacaoEntity> solicitacaoList;
}
