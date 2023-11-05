package stag.ease.stagease.entityTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SolicitacaoEntity;
import stag.ease.stagease.entity.EquipamentoEntity;

import java.util.List;

@SpringBootTest
public class AreaEntityTest {
    @Test
    void testEntitySetter() {
        AreaEntity entity = new AreaEntity();

        entity.setNomeArea("Enfermagem");
        entity.setSolicitacaoList(List.of(new SolicitacaoEntity()));
        entity.setEquipamentoList(List.of(new EquipamentoEntity()));

        Assertions.assertEquals("Enfermagem", entity.getNomeArea());
        Assertions.assertEquals(1, entity.getSolicitacaoList().size());
        Assertions.assertEquals(1, entity.getEquipamentoList().size());
    }
}
