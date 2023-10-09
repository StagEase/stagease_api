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
import stag.ease.stagease.controller.UBSController;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.entity.UBSEntity;
import stag.ease.stagease.entity.enums.Distrito;
import stag.ease.stagease.repository.UBSRepository;
import stag.ease.stagease.service.UBSService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UBSControllerTest {
    @InjectMocks
    private UBSController controller;
    @Mock
    private UBSService service;
    @Mock
    private UBSRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private UBSDTO dto;
    private UBSEntity entity;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new UBSDTO("Centro", "Carlos", Distrito.NOROESTE, List.of("+55 45 99988-7766"), List.of(new AreaDTO("Enfermagem")), "Descrição");

        List<UBSDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);

        when(service.getById(anyLong())).thenReturn(entity);
        when(service.getByNomeUBS(anyString())).thenReturn(entity);
        when(service.getAll()).thenReturn(dtoList);
        when(service.create(any(UBSDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(UBSDTO.class))).thenReturn(dto);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testGetById() {
        ResponseEntity<UBSDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).getById(id);
    }

    @Test
    void testGetByNomeArea() {
        String nomeUBS = "Centro";
        ResponseEntity<UBSDTO> response = controller.getByNomeUBS(nomeUBS);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).getByNomeUBS(nomeUBS);
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<UBSDTO>> responseEntity = controller.getAll();

        List<UBSDTO> dtoList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(dtoList);

        verify(service).getAll();
    }

    @Test
    void testCreate() {
        ResponseEntity<UBSDTO> response = controller.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).create(dto);
    }

    @Test
    void testUpdate() {
        ResponseEntity<UBSDTO> response = controller.update(id, dto);

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
