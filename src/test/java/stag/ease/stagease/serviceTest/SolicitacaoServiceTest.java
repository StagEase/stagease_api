package stag.ease.stagease.serviceTest;

import jakarta.persistence.EntityNotFoundException;
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
import stag.ease.stagease.entity.enums.Situacao;
import stag.ease.stagease.repository.SolicitacaoRepository;
import stag.ease.stagease.service.SolicitacaoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SolicitacaoServiceTest {
    @InjectMocks
    private  SolicitacaoService service;
    @MockBean
    private SolicitacaoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private final Long idNaoExistente = 2L;
    private SolicitacaoDTO dto;
    private SolicitacaoEntity entity;
    private SolicitacaoEntity entity2;
    private List<SolicitacaoEntity> entityList;
    private SolicitacaoEntity updatedEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        initClass();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
        when(repository.findAll()).thenReturn(entityList);
        when(repository.findById(id)).thenReturn(Optional.of(entity));
    }

    @Test
    void testGetByIdExistente() {
        SolicitacaoEntity database = service.getById(id);

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
        List<SolicitacaoEntity> database = service.getAll();

        assertEquals(2, database.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(repository.save(any())).thenReturn(entity);

        SolicitacaoEntity createdEntity = service.create(entity);

        assertNotNull(createdEntity);
//        assertEquals("Enfermagem", createdEntity.getNomeArea());

        verify(repository, times(1)).save(entity);
    }

    @Test
    void testUpdate() {
        when(repository.save(any())).thenReturn(updatedEntity);

        SolicitacaoEntity result = service.update(id, updatedEntity);

        assertNotNull(result);
        assertEquals(id, result.getId());
//        assertEquals("Fisioterapia", result.getNomeArea());

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDeleteById() {
        service.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    private void initClass() {
<<<<<<< Updated upstream
        dto = new SolicitacaoDTO();

        dto.setId(id);
        dto.setUbs(new UBSDTO());
        dto.setArea(new AreaDTO());
        dto.setSupervisor(new SupervisorDTO());
=======
        modelMapper = new ModelMapper();
        EquipamentoDTO equipamento = new EquipamentoDTO();
        AreaDTO area = new AreaDTO();
        SupervisorDTO supervisor = new SupervisorDTO();
        InstituicaoDeEnsinoDTO ie = new InstituicaoDeEnsinoDTO();

        dto = new SolicitacaoDTO();

        dto.setId(1L);
        dto.setEquipamento(equipamento);
        dto.setArea(area);
        dto.setSupervisor(supervisor);
>>>>>>> Stashed changes
        dto.setQntdEstagiarios(4);
        dto.setInstituicaoDeEnsino(new InstituicaoDeEnsinoDTO());
        dto.setDataInicio(LocalDate.now());
        dto.setDataFim(LocalDate.now());
        dto.setInicioExpediente(LocalTime.now());
        dto.setFimExpediente(LocalTime.now());
        dto.setSituacao(Situacao.LIBERADO);

<<<<<<< Updated upstream
        entity = new SolicitacaoEntity();

        entity.setId(id);
        entity.setUbs(new UBSEntity());
        entity.setArea(new AreaEntity());
        entity.setSupervisor(new SupervisorEntity());
=======
        EquipamentoEntity equipamentoEntity = new EquipamentoEntity();
        AreaEntity areaEntity = new AreaEntity();
        SupervisorEntity supervisorEntity = new SupervisorEntity();
        InstituicaoDeEnsinoEntity ieEntity = new InstituicaoDeEnsinoEntity();

        entity = new SolicitacaoEntity();

        entity.setEquipamento(equipamentoEntity);
        entity.setArea(areaEntity);
        entity.setSupervisor(supervisorEntity);
>>>>>>> Stashed changes
        entity.setQntdEstagiarios(4);
        entity.setInstituicaoDeEnsino(new InstituicaoDeEnsinoEntity());
        entity.setDataInicio(LocalDate.now());
        entity.setDataFim(LocalDate.now());
        entity.setInicioExpediente(LocalTime.now());
        entity.setFimExpediente(LocalTime.now());
        entity.setSituacao(Situacao.LIBERADO);

        entity2 = new SolicitacaoEntity();

        entity2.setId(id);
        entity2.setUbs(new UBSEntity());
        entity2.setArea(new AreaEntity());
        entity2.setSupervisor(new SupervisorEntity());
        entity2.setQntdEstagiarios(4);
        entity2.setInstituicaoDeEnsino(new InstituicaoDeEnsinoEntity());
        entity2.setDataInicio(LocalDate.now());
        entity2.setDataFim(LocalDate.now());
        entity2.setInicioExpediente(LocalTime.now());
        entity2.setFimExpediente(LocalTime.now());
        entity2.setSituacao(Situacao.LIBERADO);

        entityList = new ArrayList<>();
        entityList.add(entity);

        updatedEntity = new SolicitacaoEntity();

        updatedEntity.setId(id);
        updatedEntity.setUbs(new UBSEntity());
        updatedEntity.setArea(new AreaEntity());
        updatedEntity.setSupervisor(new SupervisorEntity());
        updatedEntity.setQntdEstagiarios(4);
        updatedEntity.setInstituicaoDeEnsino(new InstituicaoDeEnsinoEntity());
        updatedEntity.setDataInicio(LocalDate.now());
        updatedEntity.setDataFim(LocalDate.now());
        updatedEntity.setInicioExpediente(LocalTime.now());
        updatedEntity.setFimExpediente(LocalTime.now());
        updatedEntity.setSituacao(Situacao.LIBERADO);
    }
}

