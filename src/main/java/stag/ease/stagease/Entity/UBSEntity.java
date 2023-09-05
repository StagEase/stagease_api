package stag.ease.stagease.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stag.ease.stagease.Entity.Enum.Distrito;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "ubs", schema = "stagease")
public class UBSEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private boolean ativo;

    @Column(length = 50, nullable = false, unique = true)
    private String nomeUBS;

    @Column(length = 50, nullable = false)
    private String gerente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Distrito distrito;

    @Column
    private List<String> contatoList;

    @ManyToMany
    @JoinTable(name = "ubs_supervisor",
            joinColumns = @JoinColumn(name = "ubs_id"),
            inverseJoinColumns = @JoinColumn(name = "supervisor_id"))
    private List<SupervisorEntity> supervisorList;

    @ManyToMany
    @JoinTable(name = "ubs_area",
            joinColumns = @JoinColumn(name = "ubs_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<AreaEntity> areaList;

    @JsonIgnore
    @OneToMany(mappedBy = "ubs")
    private List<SolicitacaoEntity> solicitacaoList;

    @Column
    private String descricao;
}
