package stag.ease.stagease.serviceTest;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        AreaDTO dto = new AreaDTO("Enfermagem");
        dto.setId(id);

        AreaEntity entity = new AreaEntity("Enfermagem", null, null);
        entity.setId(id);
        AreaEntity entity2 = new AreaEntity("Fisioterapia", null, null);

        List<AreaEntity> entityList = Arrays.asList(entity, entity2);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findAll()).thenReturn(entityList);
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
    void testFindAll() {
        List<AreaEntity> result = service.getAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        AreaEntity entityToCreate = new AreaEntity("Nova Área", null, null);

        when(repository.save(any(AreaEntity.class))).thenReturn(entityToCreate);

        AreaEntity createdEntity = service.create(entityToCreate);

        assertNotNull(createdEntity);
        assertEquals("Nova Área", createdEntity.getNomeArea());
        verify(repository, times(1)).save(entityToCreate);
    }

    @Test
    void testUpdate() {
        AreaEntity existingEntity = new AreaEntity("Área Existente", null, null);
        when(repository.findById(id)).thenReturn(Optional.of(existingEntity));

        AreaEntity updatedData = new AreaEntity("Área Atualizada", null, null);

        AreaEntity updatedEntity = service.update(id, updatedData);

        assertNotNull(updatedEntity);
        assertEquals("Área Atualizada", updatedEntity.getNomeArea());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(existingEntity);
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
