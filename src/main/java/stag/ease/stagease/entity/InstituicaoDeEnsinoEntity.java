package stag.ease.stagease.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.List;

@Getter
@Setter
@Entity
//@Audited
@Table(name = "instituicao_de_ensino")
//@AuditTable(value = "tb_instituicao_de_ensino_audit", schema = "audit")
public class InstituicaoDeEnsinoEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String nomeIe;
}
