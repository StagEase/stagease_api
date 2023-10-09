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
import stag.ease.stagease.controller.SupervisorController;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.repository.SupervisorRepository;
import stag.ease.stagease.service.SupervisorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class SupervisorControllerTest {

    @InjectMocks
    private SupervisorController controller;
    @Mock
    private SupervisorService service;
    @Mock
    private SupervisorRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private SupervisorDTO dto;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new SupervisorDTO("Marcelo", "matricula1", "Descrição");
        List<SupervisorDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);

        when(service.getById(anyLong())).thenReturn(dto);
        when(service.findByMatricula(anyString())).thenReturn(dto);
        when(service.findByNomeSupervisor(anyString())).thenReturn(dto);
        when(service.getAll()).thenReturn(dtoList);
        when(service.create(any(SupervisorDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(SupervisorDTO.class))).thenReturn(dto);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testGetById() {
        ResponseEntity<SupervisorDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).getById(id);
    }

    @Test
    void testGetByMatricula() {
        String matricula = "matricula1";
        ResponseEntity<SupervisorDTO> response = controller.getByNomeMatricula(matricula);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).findByMatricula(matricula);
    }

    @Test
    void testGetByNomeSupervisor() {
        String nomeSupervisor = "Marcelo";
        ResponseEntity<SupervisorDTO> response = controller.getByNomeSupervisor(nomeSupervisor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).findByNomeSupervisor(nomeSupervisor);
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<SupervisorDTO>> responseEntity = controller.getAll();

        List<SupervisorDTO> dtoList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(dtoList);

        verify(service).getAll();
    }

    @Test
    void testCreate() {
        ResponseEntity<SupervisorDTO> response = controller.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).create(dto);
    }

    @Test
    void testUpdate() {
        ResponseEntity<SupervisorDTO> response = controller.update(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).update(id, dto);
    }

    @Test
    void testDelete() {
        ResponseEntity<HttpStatus> response = controller.delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(service).deleteById(id);
    }
}
