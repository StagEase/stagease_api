package stag.ease.stagease.serviceTest;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.EquipamentoDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.entity.EquipamentoEntity;
import stag.ease.stagease.entity.enums.Distrito;
import stag.ease.stagease.repository.EquipamentoRepository;
import stag.ease.stagease.service.EquipamentoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipamentoServiceTest {
    @InjectMocks
    private EquipamentoService service;
    @Mock
    private EquipamentoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private EquipamentoDTO dto;
    private EquipamentoEntity entity;
    private EquipamentoEntity entity2;
    private List<EquipamentoEntity> entityList;
    private EquipamentoEntity updatedEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initClass();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findByNomeEquipamento("Centro")).thenReturn(entity);
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testGetByIdExistente() {
        EquipamentoEntity database = service.getById(id);

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
    void testGetByNomeEquipamento() {
        EquipamentoEntity database = service.getByNomeEquipamento("Centro");

        assertEquals("Centro", database.getNomeEquipamento());
    }

    @Test
    void testFindAll() {
        List<EquipamentoEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        EquipamentoEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
        assertEquals("Centro", createdEntity.getNomeEquipamento());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        EquipamentoEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Vila A", result.getNomeEquipamento());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    private void initClass() {
        dto = new EquipamentoDTO();

        dto.setId(id);
        dto.setNomeEquipamento("Centro");
        dto.setGerente("Marcelo");
        dto.setDistrito(Distrito.NORTE);
        dto.setContatoList(null);
        dto.setAreaList(List.of(new AreaDTO()));
        dto.setDescricao("Descrição");

        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setId(id);
        areaEntity.setNomeArea("Enfermagem");

        SupervisorEntity supervisorEntity = new SupervisorEntity();
        supervisorEntity.setId(id);
        supervisorEntity.setNomeSupervisor("Zé");

        entity = new EquipamentoEntity();

        entity.setId(id);
        entity.setNomeEquipamento("Centro");
        entity.setGerente("Marcelo");
        entity.setDistrito(Distrito.NORTE);
        entity.setContatoList(null);
        entity.setSupervisorList(List.of(supervisorEntity));
        entity.setAreaList(List.of(areaEntity));
        entity.setSolicitacaoList(null);
        entity.setDescricao("Descrição");

        entity2 = new EquipamentoEntity();

        entity2.setId(2L);
        entity2.setNomeEquipamento("Centro");
        entity2.setGerente("Marcelo");
        entity2.setDistrito(Distrito.NORTE);
        entity2.setContatoList(null);
        entity2.setSupervisorList(List.of(supervisorEntity));
        entity2.setAreaList(List.of(areaEntity));
        entity2.setSolicitacaoList(null);
        entity2.setDescricao("Descrição");

        entityList = Arrays.asList(entity, entity2);

        updatedEntity = new EquipamentoEntity();

        updatedEntity.setId(id);
        updatedEntity.setNomeEquipamento("Vila A");
    }
}
