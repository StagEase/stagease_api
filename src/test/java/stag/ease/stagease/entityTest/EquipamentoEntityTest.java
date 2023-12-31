package stag.ease.stagease.entityTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SolicitacaoEntity;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.entity.EquipamentoEntity;
import stag.ease.stagease.entity.enums.Distrito;

import java.util.List;

class EquipamentoEntityTest {
    @Test
    void testEntitySetter() {
        EquipamentoEntity entity = new EquipamentoEntity();

        entity.setNomeEquipamento("Centro");
        entity.setGerente("Marcelo");
        entity.setDistrito(Distrito.NORTE);
        entity.setContatoList(null);
        entity.setSupervisorList(List.of(new SupervisorEntity()));
        entity.setAreaList(List.of(new AreaEntity()));
        entity.setSolicitacaoList(List.of(new SolicitacaoEntity()));
        entity.setDescricao("Descrição");

        Assertions.assertEquals("Centro", entity.getNomeEquipamento());
        Assertions.assertEquals("Marcelo", entity.getGerente());
        Assertions.assertEquals(Distrito.NORTE, entity.getDistrito());
        Assertions.assertNull(entity.getContatoList());
        Assertions.assertEquals(1, entity.getSupervisorList().size());
        Assertions.assertEquals(1, entity.getAreaList().size());
        Assertions.assertEquals(1, entity.getSolicitacaoList().size());
        Assertions.assertEquals("Descrição", entity.getDescricao());
    }
}
