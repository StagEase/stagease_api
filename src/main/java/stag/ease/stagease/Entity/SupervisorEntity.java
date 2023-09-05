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
@Table(name = "supervisor", schema = "stagease")
public class SupervisorEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private boolean ativo;

    @Column(nullable = false)
    private String nomeSupervisor;

    @Column(nullable = false, unique = true)
    private String matricula;

    @OneToMany(mappedBy = "supervisor")
    @JsonIgnore
    private List<SolicitacaoEntity> solicitacaoList;

    @ManyToMany
    @JoinTable(
            name = "supervisor_ubs",
            joinColumns = @JoinColumn(name = "supervisor_id"),
            inverseJoinColumns = @JoinColumn(name = "ubs_id")
    )
    private List<UBSEntity> ubsList;

    private String descricao;
}
