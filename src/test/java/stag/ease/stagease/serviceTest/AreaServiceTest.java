package stag.ease.stagease.serviceTest;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.repository.AreaRepository;
import stag.ease.stagease.service.AreaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AreaServiceTest {
    @InjectMocks
    private AreaService service;
    @Mock
    private AreaRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private AreaDTO dto;
    private AreaEntity entity;
    private AreaEntity entity2;
    private List<AreaEntity> entityList;
    private AreaEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelMapper = new ModelMapper();

        dto = new AreaDTO("Enfermagem");
        dto.setId(id);

        entity = new AreaEntity("Enfermagem", null, null);
        entity.setId(id);

        entity2 = new AreaEntity("Fisioterapia", null, null);

        entityList = Arrays.asList(entity, entity2);

        updatedEntity = new AreaEntity();
        updatedEntity.setId(id);
        updatedEntity.setNomeArea("Fisioterapia");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByNomeArea("Enfermagem")).thenReturn(entity);
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testGetByIdExistente() {
        AreaEntity database = service.getById(id);

        assertNotNull(database);
        assertEquals(id, database.getId());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void testGetByIdNaoExistente() {
        assertThrows(EntityNotFoundException.class, () -> service.getById(idNaoExistente));

        verify(repository, times(1)).findById(idNaoExistente);
    }

    @Test
    void testGetByNomeUBS() {
        AreaEntity database = service.getByNomeArea("Enfermagem");

        assertEquals("Enfermagem", database.getNomeArea());
    }

    @Test
    void testFindAll() {
        List<AreaEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        AreaEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("Enfermagem", createdEntity.getNomeArea());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        AreaEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Fisioterapia", result.getNomeArea());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
