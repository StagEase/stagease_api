package stag.ease.stagease.serviceTest;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.SupervisorDTO;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.entity.UBSEntity;
import stag.ease.stagease.entity.enums.Distrito;
import stag.ease.stagease.repository.UBSRepository;
import stag.ease.stagease.service.UBSService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UBSServiceTest {
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    @InjectMocks
    private UBSService service;
    @Mock
    private UBSRepository repository;
<<<<<<< HEAD
    private UBSDTO dto;
    private UBSEntity entity;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
=======
    @Mock
    private ModelMapper modelMapper;
>>>>>>> 07207122276f02dbecfd48bc31e60bbfb5066485

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
<<<<<<< HEAD
        initClass();
=======

        UBSDTO dto = new UBSDTO("Centro", "Carlos", Distrito.NOROESTE,
                List.of("Contato1", "Contato2"),
                List.of(new AreaDTO("Enfermagem")),
                List.of(new SupervisorDTO("Gustavo", "1233321", "")),
                "Descrição");
        dto.setId(id);

        UBSEntity entity = new UBSEntity();
        UBSEntity entity2 = new UBSEntity();

        List<UBSEntity> entityList = Arrays.asList(entity, entity2);
>>>>>>> 07207122276f02dbecfd48bc31e60bbfb5066485

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
    }

    @Test
    void testGetByIdExistente() {
        UBSEntity database = service.getById(id);

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
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    private void initClass() {
        dto = new UBSDTO();

        dto.setId(1L);
        dto.setNomeUBS("Centro");
        dto.setGerente("Marcelo");
        dto.setDistrito(Distrito.NORTE);
        dto.setContatoList(null);
        dto.setAreaList(List.of(new AreaDTO("Enfermagem")));
        dto.setDescricao("Descrição");

        entity = new UBSEntity();

        entity.setId(1L);
        entity.setNomeUBS("Centro");
        entity.setGerente("Marcelo");
        entity.setDistrito(Distrito.NORTE);
        entity.setContatoList(null);
        entity.setSupervisorList(List.of(new SupervisorEntity("Kaue", "12345", null, null, "Descrição")));
        entity.setAreaList(List.of(new AreaEntity("Enfermagem", null, null)));
        entity.setSolicitacaoList(null);
        entity.setDescricao("Descrição");
    }
}
