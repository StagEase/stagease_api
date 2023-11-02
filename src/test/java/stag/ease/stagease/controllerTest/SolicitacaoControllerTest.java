package stag.ease.stagease.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import stag.ease.stagease.controller.SolicitacaoController;
import stag.ease.stagease.dto.*;
import stag.ease.stagease.entity.*;
import stag.ease.stagease.entity.enums.Situacao;
import stag.ease.stagease.service.SolicitacaoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SolicitacaoControllerTest {
    private final Long id = 1L;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private SolicitacaoController controller;
    @Mock
    private SolicitacaoService service;
    @Mock
    private ModelMapper modelMapper;
    private SolicitacaoDTO dto;
    private SolicitacaoEntity entity;
    private List<SolicitacaoEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    void inject() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        initClass();
    }

    @Test
    void shouldCreate() throws Exception {
        String areaDTOJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/area")
                        .content(areaDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetById() throws Exception {
        when(service.getById(id)).thenReturn(entity);
        mockMvc.perform(get("/solicitacao/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/solicitacao/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdate() throws Exception {
        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/solicitacao/{id}", id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/solicitacao/{id}", id))
                .andExpect(status().isOk());
    }

    private void initClass() {
        dto = new SolicitacaoDTO();

        dto.setUbs(new UBSDTO());
        dto.setArea(new AreaDTO());
        dto.setSupervisor(new SupervisorDTO());
        dto.setQntdEstagiarios(4);
        dto.setInstituicaoDeEnsino(new InstituicaoDeEnsinoDTO());
        dto.setDataInicio(LocalDate.now());
        dto.setDataFim(LocalDate.now());
        dto.setInicioExpediente(LocalTime.now());
        dto.setFimExpediente(LocalTime.now());
        dto.setSituacao(Situacao.LIBERADO);

        entity = new SolicitacaoEntity();
        entity.setUbs(new UBSEntity());
        entity.setArea(new AreaEntity());
        entity.setSupervisor(new SupervisorEntity());
        entity.setQntdEstagiarios(4);
        entity.setInstituicaoDeEnsino(new InstituicaoDeEnsinoEntity());
        entity.setDataInicio(LocalDate.now());
        entity.setDataFim(LocalDate.now());
        entity.setInicioExpediente(LocalTime.now());
        entity.setFimExpediente(LocalTime.now());
        entity.setSituacao(Situacao.LIBERADO);

        entityList = new ArrayList<>();
        entityList.add(entity);
    }
}
