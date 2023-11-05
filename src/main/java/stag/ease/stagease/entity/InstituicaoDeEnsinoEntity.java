package stag.ease.stagease.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "instituicao_de_ensino", schema = "stagease")
public class InstituicaoDeEnsinoEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String nomeIe;

    @OneToMany(mappedBy = "instituicaoDeEnsino")
    private List<SolicitacaoEntity> solicitacaoList;
}
