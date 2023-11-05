package stag.ease.stagease.dtoTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.dto.EquipamentoDTO;
import stag.ease.stagease.entity.enums.Distrito;

import java.util.List;

@SpringBootTest
public class UBSDtoTest {
    @Test
    void testDTO() {
        EquipamentoDTO dto = new EquipamentoDTO("Centro", "Marcelo", Distrito.NORTE, null, List.of(new AreaDTO()), List.of(new SupervisorDTO()), "Descrição");

        Assertions.assertEquals("Centro", dto.getNomeUBS());
        Assertions.assertEquals("Marcelo", dto.getGerente());
        Assertions.assertEquals(Distrito.NORTE, dto.getDistrito());
        Assertions.assertNull(dto.getContatoList());
        Assertions.assertEquals(1, dto.getAreaList().size());
        Assertions.assertEquals(1, dto.getSupervisorList().size());
        Assertions.assertEquals("Descrição", dto.getDescricao());
    }

    @Test
    void testDTOSetter() {
        EquipamentoDTO dto = new EquipamentoDTO();

        dto.setNomeUBS("Centro");
        dto.setGerente("Marcelo");
        dto.setDistrito(Distrito.NORTE);
        dto.setContatoList(null);
        dto.setAreaList(List.of(new AreaDTO()));
        dto.setSupervisorList(List.of(new SupervisorDTO()));
        dto.setDescricao("Descrição");

        Assertions.assertEquals("Centro", dto.getNomeUBS());
        Assertions.assertEquals("Marcelo", dto.getGerente());
        Assertions.assertEquals(Distrito.NORTE, dto.getDistrito());
        Assertions.assertNull(dto.getContatoList());
        Assertions.assertEquals(1, dto.getAreaList().size());
        Assertions.assertEquals(1, dto.getSupervisorList().size());
        Assertions.assertEquals("Descrição", dto.getDescricao());
    }
}
