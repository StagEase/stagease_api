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
import stag.ease.stagease.entity.enums.Situacao;
import stag.ease.stagease.repository.SolicitacaoRepository;
import stag.ease.stagease.service.SolicitacaoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SolicitacaoServiceTest {
    @InjectMocks
    private SolicitacaoService service;
    @MockBean
    private SolicitacaoService serviceTest;
    @MockBean
    private SolicitacaoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private SolicitacaoDTO dto;
    private SolicitacaoEntity entity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        initClass();
    }

    @Test
    void testList() {
        List<SolicitacaoEntity> entityList = new ArrayList<>();
        entityList.add(entity);

        when(repository.findAll()).thenReturn(entityList);

        List<SolicitacaoDTO> result = service.getList();
        assertEquals(1, result.size());
    }

    @Test
    void testGetId() {
        Long id = 1L;
        when(serviceTest.getById(id)).thenReturn(dto);

        SolicitacaoDTO result = serviceTest.getById(id);
        assertNotNull(result);
        assertEquals(dto.getQntdEstagiarios(), result.getQntdEstagiarios());
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
        UBSDTO ubs = new UBSDTO();
        AreaDTO area = new AreaDTO();
        SupervisorDTO supervisor = new SupervisorDTO();
        InstituicaoDeEnsinoDTO ie = new InstituicaoDeEnsinoDTO();

        dto = new SolicitacaoDTO();

        dto.setId(1L);
        dto.setUbs(ubs);
        dto.setArea(area);
        dto.setSupervisor(supervisor);
        dto.setQntdEstagiarios(4);
        dto.setInstituicaoDeEnsino(ie);
        dto.setDataInicio(LocalDate.now());
        dto.setDataFim(LocalDate.now());
        dto.setInicioExpediente(LocalTime.now());
        dto.setFimExpediente(LocalTime.now());
        dto.setSituacao(Situacao.LIBERADO);

        UBSEntity ubsEntity = new UBSEntity();
        AreaEntity areaEntity = new AreaEntity();
        SupervisorEntity supervisorEntity = new SupervisorEntity();
        InstituicaoDeEnsinoEntity ieEntity = new InstituicaoDeEnsinoEntity();

        entity = new SolicitacaoEntity();

        entity.setUbs(ubsEntity);
        entity.setArea(areaEntity);
        entity.setSupervisor(supervisorEntity);
        entity.setQntdEstagiarios(4);
        entity.setInstituicaoDeEnsino(ieEntity);
        entity.setDataInicio(LocalDate.now());
        entity.setDataFim(LocalDate.now());
        entity.setInicioExpediente(LocalTime.now());
        entity.setFimExpediente(LocalTime.now());
        entity.setSituacao(Situacao.LIBERADO);

    }
}

