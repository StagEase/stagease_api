package stag.ease.stagease.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "area", schema = "stagease")
public class AreaEntity extends AbstractEntity{
    @Column(length = 50, nullable = false, unique = true)
    private String nomeArea;
}
