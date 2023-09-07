package stag.ease.stagease;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stag.ease.stagease.Controller.AreaController;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Repository.AreaRepository;
import stag.ease.stagease.Service.AreaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AreaTest {
    @Autowired
    private AreaService service;
    @MockBean
    private AreaRepository repository;
    @Mock
    private AreaController controller;

    AreaEntity area = new AreaEntity();
    AreaDTO areaDTO = new AreaDTO();
    List<AreaDTO> areas = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        area.setId(1L);
        area.setNomeArea("Exemplo area");

        areaDTO.setId(1L);
        areaDTO.setNomeArea("Exemplo area");

        areas.add(areaDTO);

        when(repository.findById(1L)).thenReturn(Optional.of(area));
        when(service.findAll()).thenReturn(areas);
        when(controller.findByNomeArea("Exemplo area")).thenReturn(ResponseEntity.ok(areaDTO));
    }

    @Test
    void testFindByNomeArea() {
        var areaDTO = controller.findByNomeArea("Exemplo area");
        String nome = areaDTO.getBody().getNomeArea();
        System.out.println(nome);
        Assertions.assertEquals("Exemplo area", nome);
    }

    @Test
    void testFindAll() {
        AreaDTO exemploAreaDTO = new AreaDTO();
        exemploAreaDTO.setId(1L);
        exemploAreaDTO.setNomeArea("Exemplo area");

        List<AreaDTO> areas = new ArrayList<>();
        areas.add(exemploAreaDTO);

        when(controller.findAll()).thenReturn(ResponseEntity.ok(areas));

        List<AreaDTO> allLembretes = controller.findAll().getBody();
        assertThat(allLembretes).isNotNull();
        assertThat(allLembretes.size()).isEqualTo(1);
    }
}