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
public class InstituicaoDeEnsinoEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private boolean ativo;

    @Column(nullable = false, unique = true)
    private String nomeIE;

    @OneToMany(mappedBy = "instituicaoDeEnsino")
    @JsonIgnore
    private List<SolicitacaoEntity> solicitacaoList;
}
