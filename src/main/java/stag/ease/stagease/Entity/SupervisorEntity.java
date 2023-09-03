package stag.ease.stagease.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "supervisor", schema = "stagease")
public class SupervisorEntity extends AbstractEntity{
    @OneToMany(mappedBy = "supervisor")
    private List<SolicitacaoEntity> solicitacaoList;
    @ManyToMany(mappedBy = "supervisorList")
    private List<UBSEntity> ubsList;
}
