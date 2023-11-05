package stag.ease.stagease.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.dto.*;
import stag.ease.stagease.entity.*;
import stag.ease.stagease.repository.InstituicaoDeEnsinoRepository;
import stag.ease.stagease.service.InstituicaoDeEnsinoService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class InstituicaoDeEnsinoServiceTest {

    @InjectMocks
    private InstituicaoDeEnsinoService service;
    @MockBean
    private InstituicaoDeEnsinoService serviceTest;
    @MockBean
    private InstituicaoDeEnsinoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private InstituicaoDeEnsinoDTO dto;
    private InstituicaoDeEnsinoEntity entity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        initClass();
    }

    @Test
    void testList() {
        List<InstituicaoDeEnsinoEntity> entityList = new ArrayList<>();
        entityList.add(entity);

        when(repository.findAll()).thenReturn(entityList);

        List<InstituicaoDeEnsinoDTO> result = service.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetId() {
        Long id = 1L;
        when(serviceTest.getById(id)).thenReturn(dto);

        InstituicaoDeEnsinoDTO result = serviceTest.getById(id);
        assertNotNull(result);
        assertEquals(dto.getNomeIe(), result.getNomeIe());
    }

    @Test
    void testCreateException() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.create(dto));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testUpdateException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, ()-> service.update(2L, dto));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    private void initClass() {
        modelMapper = new ModelMapper();
        InstituicaoDeEnsinoDTO ie = new InstituicaoDeEnsinoDTO();

        dto = new InstituicaoDeEnsinoDTO();

        dto.setId(1L);
        dto.setNomeIe("Uniamerica");

        InstituicaoDeEnsinoEntity ieEntity = new InstituicaoDeEnsinoEntity();

        entity = new InstituicaoDeEnsinoEntity();


        entity.setNomeIe("Uniamerica");
    }
}
