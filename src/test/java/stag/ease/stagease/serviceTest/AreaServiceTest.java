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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        AreaDTO dto = new AreaDTO("Enfermagem");

        AreaEntity entity = new AreaEntity("Enfermagem", null, null);
        AreaEntity entity2 = new AreaEntity("Fisioterapia", null, null);

        List<AreaEntity> entityList = Arrays.asList(entity, entity2);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        when(modelMapper.map(entity, AreaDTO.class)).thenReturn(dto);
    }

    @Test
    void testGetByIdExistente() {
        AreaDTO dtoBanco = service.getById(id);

        assertNotNull(dtoBanco);
        assertEquals(id, dtoBanco.getId());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void testGetByIdNaoExistente() {
        assertThrows(EntityNotFoundException.class, () -> service.getById(idNaoExistente));

        verify(repository, times(1)).findById(idNaoExistente);
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
