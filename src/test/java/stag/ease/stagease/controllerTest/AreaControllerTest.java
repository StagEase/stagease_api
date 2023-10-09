package stag.ease.stagease.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import stag.ease.stagease.controller.AreaController;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.repository.AreaRepository;
import stag.ease.stagease.service.AreaService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class AreaControllerTest {
    @InjectMocks
    private AreaController controller;
    @Mock
    private AreaService service;
    @Mock
    private AreaRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private AreaDTO dto;
    private AreaEntity entity;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new AreaDTO("Enfermagem");
        entity = new AreaEntity("Enfermagem", null, null);
        List<AreaDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        List<AreaEntity> entityList = new ArrayList<>();
        entityList.add(entity);

        when(service.getById(anyLong())).thenReturn(entity);
        when(service.getByNomeArea(anyString())).thenReturn(entity);
        when(service.getAll()).thenReturn(entityList);
        when(service.create(any(AreaEntity.class))).thenReturn(entity);
        when(service.update(anyLong(), any(AreaEntity.class))).thenReturn(entity);
        doNothing().when(service).deleteById(anyLong());
    }

    /*@Test
    void testGetById() {
    }

    @Test
    void testGetByNomeArea() {
    }

    @Test
    void testGetAll() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testDelete() {
    }*/
}