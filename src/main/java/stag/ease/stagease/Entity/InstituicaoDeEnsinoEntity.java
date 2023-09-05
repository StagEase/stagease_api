package stag.ease.stagease.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "instituicao_de_ensino", schema = "stagease")
public class InstituicaoDeEnsinoEntity extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String nomeIE;

    @OneToMany(mappedBy = "instituicaoDeEnsino")
    @JsonIgnore
    private List<SolicitacaoEntity> solicitacaoList;
}
