package stag.ease.stagease.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO {
    private Long id;
    private LocalDateTime cadastro;
    private LocalDateTime atualizacao;
    private boolean ativo;
}
