package stag.ease.stagease.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stag.ease.stagease.controller.AreaController;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.repository.AreaRepository;
import stag.ease.stagease.service.AreaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new AreaDTO();
        List<AreaDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);

        when(service.getById(anyLong())).thenReturn(dto);
        when(service.getByNomeArea(anyString())).thenReturn(dto);
        when(service.getAll()).thenReturn(dtoList);
        when(service.create(any(AreaDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(AreaDTO.class))).thenReturn(dto);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<AreaDTO>> responseEntity = controller.getAll();

        List<AreaDTO> dtoList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(dtoList);
    }

    @Test
    void testGetById() {
        ResponseEntity<AreaDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testGetByNomeArea() {
        String nomeArea = "Enfermagem";
        ResponseEntity<AreaDTO> response = controller.getByNomeArea(nomeArea);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testCreate() {
        ResponseEntity<AreaDTO> response = controller.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testUpdate() {
        ResponseEntity<AreaDTO> response = controller.update(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDelete() {
        ResponseEntity<HttpStatus> response = controller.delete(id);

        verify(service).deleteById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}