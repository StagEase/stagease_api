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
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SupervisorEntity;
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
    private UBSDTO dto;
    private UBSEntity entity;
    private UBSEntity entity2;
    private List<UBSEntity> entityList;
    private UBSEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initClass();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByNomeUBS("Centro")).thenReturn(entity);
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
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
    void testGetByNomeUBS() {
        UBSEntity database = service.getByNomeUBS("Centro");

        assertEquals("Centro", database.getNomeUBS());
    }

    @Test
    void testFindAll() {
        List<UBSEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        UBSEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("Centro", createdEntity.getNomeUBS());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        UBSEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Vila A", result.getNomeUBS());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    private void initClass() {
        dto = new UBSDTO();

        dto.setId(id);
        dto.setNomeUBS("Centro");
        dto.setGerente("Marcelo");
        dto.setDistrito(Distrito.NORTE);
        dto.setContatoList(null);
        dto.setAreaList(List.of(new AreaDTO("Enfermagem")));
        dto.setDescricao("Descrição");

        entity = new UBSEntity();

        entity.setId(id);
        entity.setNomeUBS("Centro");
        entity.setGerente("Marcelo");
        entity.setDistrito(Distrito.NORTE);
        entity.setContatoList(null);
        entity.setSupervisorList(List.of(new SupervisorEntity("Kaue", "12345", null, null, "Descrição")));
        entity.setAreaList(List.of(new AreaEntity("Enfermagem", null, null)));
        entity.setSolicitacaoList(null);
        entity.setDescricao("Descrição");

        entity2 = new UBSEntity();

        entity2.setId(2L);
        entity2.setNomeUBS("Centro");
        entity2.setGerente("Marcelo");
        entity2.setDistrito(Distrito.NORTE);
        entity2.setContatoList(null);
        entity2.setSupervisorList(List.of(new SupervisorEntity("Kaue", "12345", null, null, "Descrição")));
        entity2.setAreaList(List.of(new AreaEntity("Enfermagem", null, null)));
        entity2.setSolicitacaoList(null);
        entity2.setDescricao("Descrição");

        entityList = Arrays.asList(entity, entity2);

        updatedEntity = new UBSEntity();

        updatedEntity.setId(id);
        updatedEntity.setNomeUBS("Vila A");
    }
}
