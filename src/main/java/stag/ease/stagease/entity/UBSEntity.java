package stag.ease.stagease.entity;

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
@Table(name = "ubs", schema = "stagease")
public class UBSEntity extends AbstractEntity{
    @Column(length = 50, nullable = false, unique = true)
    private String nomeUBS;

    @Column(length = 50, nullable = false)
    private String gerente;


    @Column
    private List<String> contatoList;

    @ManyToMany
    @JoinTable(name = "ubs_supervisor",
            schema = "stagease",
            joinColumns = @JoinColumn(name = "ubs_id"),
            inverseJoinColumns = @JoinColumn(name = "supervisor_id"))
    private List<SupervisorEntity> supervisorList;

    @ManyToMany
    @JoinTable(name = "ubs_area",
            schema = "stagease",
            joinColumns = @JoinColumn(name = "ubs_id"),
            inverseJoinColumns = @JoinColumn(name = "area_id"))
    private List<AreaEntity> areaList;

    @JsonIgnore
    @OneToMany(mappedBy = "ubs")
    private List<SolicitacaoEntity> solicitacaoList;

    @Column
    private String descricao;
}
