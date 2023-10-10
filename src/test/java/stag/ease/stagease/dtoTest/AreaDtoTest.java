package stag.ease.stagease.dtoTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import stag.ease.stagease.dto.AreaDTO;

@SpringBootTest
public class AreaDtoTest {
    @Test
    void testDTO() {
        AreaDTO dto = new AreaDTO("Enfermagem");

        Assertions.assertEquals("Enfermagem", dto.getNomeArea());
    }

    @Test
    void testDTOSetter() {
        AreaDTO dto = new AreaDTO();

        dto.setNomeArea("Enfermagem");

        Assertions.assertEquals("Enfermagem", dto.getNomeArea());
    }
}
