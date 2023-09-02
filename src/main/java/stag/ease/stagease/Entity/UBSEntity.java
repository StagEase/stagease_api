package stag.ease.stagease.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stag.ease.stagease.Entity.Enum.Distrito;

import java.util.List;

@Getter @Setter
@Entity
@Table(name = "ubs", schema = "stagease")
public class UBSEntity extends AbstractEntity{
    @Column(length = 50, nullable = false, unique = true)
    private String nomeArea;

    @Column(length = 50, nullable = false)
    private String gerente;

    @Enumerated(EnumType.STRING)
    @Column
    private Distrito distrito;

    @Column
    private List<String> contatos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ubs_supervisor",
            joinColumns = @JoinColumn(name = "ubs_id"),
            inverseJoinColumns = @JoinColumn(name = "supervisor_id"))
    private List<SupervisorEntity> supervisor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ubs_area",
            joinColumns = @JoinColumn(name = "ubs_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<AreaEntity> areasUBS;

    @OneToMany(mappedBy = "ubs")
    @JsonIgnore
    private List<SolicitacaoEntity> solicitacoes;

    @Column(length = 255)
    private String descricao;

}
