package stag.ease.stagease.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.List;

@Getter
@Setter
@Entity
//@Audited
@Table(name = "supervisor")
//@AuditTable(value = "tb_supervisor_audit", schema = "audit")
public class SupervisorEntity extends AbstractEntity {

    @Column(length = 60, nullable = false)
    private String nomeSupervisor;

    @Column(length = 40, nullable = false, unique = true)
    private String matricula;

    private String descricao;
}
