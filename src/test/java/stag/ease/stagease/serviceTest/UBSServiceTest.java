package stag.ease.stagease.serviceTest;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.entity.UBSEntity;
import stag.ease.stagease.entity.enums.Distrito;
import stag.ease.stagease.repository.UBSRepository;
import stag.ease.stagease.service.UBSService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UBSServiceTest {
    @InjectMocks
    private UBSService service;
    @Mock
    private UBSRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        UBSDTO dto = new UBSDTO("Centro", "Carlos", Distrito.NOROESTE,
                List.of("Contato1", "Contato2"),
                List.of(new AreaDTO("Enfermagem")),
                "Descrição");
        dto.setId(id);

        UBSEntity entity = new UBSEntity();
        UBSEntity entity2 = new UBSEntity();

        List<UBSEntity> entityList = Arrays.asList(entity, entity2);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());

        when(modelMapper.map(entity, UBSDTO.class)).thenReturn(dto);
    }

    @Test
    void testGetByIdExistente() {
        UBSDTO dtoBanco = service.getById(id);

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
