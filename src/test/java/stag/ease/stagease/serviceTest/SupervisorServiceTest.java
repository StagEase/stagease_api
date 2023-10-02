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
import stag.ease.stagease.dto.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.entity.InstituicaoDeEnsinoEntity;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.repository.SupervisorRepository;
import stag.ease.stagease.service.SupervisorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SupervisorServiceTest {

    @InjectMocks
    private SupervisorService service;
    @MockBean
    private SupervisorService serviceTest;
    @MockBean
    private SupervisorRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private SupervisorDTO dto;
    private SupervisorEntity entity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        initClass();
    }

    @Test
    void testList() {
        List<SupervisorEntity> entityList = new ArrayList<>();
        entityList.add(entity);

        when(repository.findAll()).thenReturn(entityList);

        List<SupervisorDTO> result = service.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetId() {
        Long id = 1L;
        when(serviceTest.getById(id)).thenReturn(dto);

        SupervisorDTO result = serviceTest.getById(id);
        assertNotNull(result);
        assertEquals(dto.getNomeSupervisor(), result.getNomeSupervisor());
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
        SupervisorDTO ie = new SupervisorDTO();
        UBSDTO ubs = new UBSDTO();

        dto = new SupervisorDTO();

        dto.setId(1L);
        dto.setNomeSupervisor("marcelo");
        dto.setMatricula("1233322");


        SupervisorEntity ieEntity = new SupervisorEntity();

        entity = new SupervisorEntity();


        entity.setNomeSupervisor("marcelo");
        dto.setMatricula("1233322");
    }
}
