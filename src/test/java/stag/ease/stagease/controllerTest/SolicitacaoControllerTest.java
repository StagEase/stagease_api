package stag.ease.stagease.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import stag.ease.stagease.controller.SolicitacaoController;
import stag.ease.stagease.dto.*;
import stag.ease.stagease.entity.enums.Situacao;
import stag.ease.stagease.repository.SolicitacaoRepository;
import stag.ease.stagease.service.SolicitacaoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SolicitacaoControllerTest {
    private final Long id = 1L;
    @InjectMocks
    private SolicitacaoController controller;
    @Mock
    private SolicitacaoService service;
    @Mock
    private SolicitacaoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;
    private SolicitacaoDTO dto;
    private List<SolicitacaoDTO> listDTO;

    @BeforeEach
    void inject() {
        MockitoAnnotations.openMocks(this);
        initClass();

        when(service.getById(anyLong())).thenReturn(dto);
        when(service.getList()).thenReturn(listDTO);
        when(service.create(any(SolicitacaoDTO.class))).thenReturn(dto);
        when(service.update(anyLong(), any(SolicitacaoDTO.class))).thenReturn(dto);
        doNothing().when(service).delete(anyLong());
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<SolicitacaoDTO>> responseEntity = controller.list();

        List<SolicitacaoDTO> listDTOTest = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(listDTOTest);
    }

    @Test
    void testGetById() {
        ResponseEntity<SolicitacaoDTO> response = controller.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testCreate() {
        ResponseEntity<SolicitacaoDTO> response = controller.create(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testUpdate() {
        ResponseEntity<SolicitacaoDTO> response = controller.update(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDelete() {
        ResponseEntity<HttpStatus> response = controller.delete(id);

        verify(service).delete(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteRuntime() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/solicitacao/{id}", id))
                .andExpect(status().is(405));
    }

    private void initClass() {
        UBSDTO ubs = new UBSDTO();
        AreaDTO area = new AreaDTO();
        SupervisorDTO supervisor = new SupervisorDTO();
        InstituicaoDeEnsinoDTO ie = new InstituicaoDeEnsinoDTO();

        dto = new SolicitacaoDTO();
        listDTO = new ArrayList<>();

        dto.setId(id);
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

        listDTO.add(dto);
    }
}
