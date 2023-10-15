package stag.ease.stagease.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instituicao_de_ensino", schema = "stagease")
public class InstituicaoDeEnsinoEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "instituicaoDeEnsino")
    private List<SolicitacaoEntity> solicitacaoList;
}
