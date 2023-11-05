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
import stag.ease.stagease.controller.InstituicaoDeEnsinoController;
import stag.ease.stagease.dto.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.repository.InstituicaoDeEnsinoRepository;
import stag.ease.stagease.service.InstituicaoDeEnsinoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class InstituicaoDeEnsinoControllerTest {

    @InjectMocks
    private InstituicaoDeEnsinoController controller;

    @Mock
    private InstituicaoDeEnsinoService service;
    @Mock
    private InstituicaoDeEnsinoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private InstituicaoDeEnsinoDTO dto;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new InstituicaoDeEnsinoDTO("Uniamerica", null);
        List<InstituicaoDeEnsinoDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);

        when(service.getById(anyLong())).thenReturn(dto);
        when(service.findByNomeIe(anyString())).thenReturn(dto);
        when(service.getAll()).thenReturn(dtoList);
        when(service.create(any(InstituicaoDeEnsinoDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(InstituicaoDeEnsinoDTO.class))).thenReturn(dto);
        doNothing().when(service).deleteById(anyLong());
    }

    @Test
    void testGetById() {
        ResponseEntity<InstituicaoDeEnsinoDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).getById(id);
    }

    @Test
    void testGetByNome() {
        String nome = "Uniamerica";
        ResponseEntity<InstituicaoDeEnsinoDTO> response = controller.getByNomeIe(nome);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).findByNomeIe(nome);
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<InstituicaoDeEnsinoDTO>> responseEntity = controller.getAll();

        List<InstituicaoDeEnsinoDTO> dtoList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(dtoList);

        verify(service).getAll();
    }

    @Test
    void testCreate() {
        ResponseEntity<InstituicaoDeEnsinoDTO> response = controller.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service).create(dto);
    }

    @Test
    void testUpdate() {
        ResponseEntity<InstituicaoDeEnsinoDTO> response = controller.update(id, dto);

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
