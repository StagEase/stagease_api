package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "area", schema = "stagease")
public class AreaEntity extends AbstractEntity {
    @Column(length = 50, nullable = false, unique = true)
    private String nomeArea;

    @ManyToMany(mappedBy = "areaList")
    private List<UBSEntity> ubsList;

    @OneToMany(mappedBy = "area")
    private List<SolicitacaoEntity> solicitacaoList;
}
