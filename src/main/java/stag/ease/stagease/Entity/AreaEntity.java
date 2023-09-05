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
@Table(name = "area", schema = "stagease")
public class AreaEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String nomeArea;

    //@ManyToMany(mappedBy = "areaList")
    //private List<UBSEntity> ubsList;

    //@OneToMany(mappedBy = "area")
    //@JsonIgnore
    //private List<SolicitacaoEntity> solicitacaoList;
}
