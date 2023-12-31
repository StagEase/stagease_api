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
import stag.ease.stagease.controller.EquipamentoController;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.EquipamentoDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.entity.EquipamentoEntity;
import stag.ease.stagease.entity.enums.Distrito;
import stag.ease.stagease.service.EquipamentoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class EquipamentoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private EquipamentoController controller;
    @Mock
    private EquipamentoService service;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private EquipamentoDTO dto;
    private EquipamentoEntity entity;
    private List<EquipamentoEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        initClass();
    }

    @Test
    void shouldCreate() throws Exception {
        String ubsDTOJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/ubs")
                        .content(ubsDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetById() throws Exception {
        when(service.getById(id)).thenReturn(entity);
        mockMvc.perform(get("/equipamento/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetByNomeEquipamento() throws Exception {
        when(service.getByNomeEquipamento("Centro")).thenReturn(entity);
        mockMvc.perform(get("/equipamento/nome/{nome}", "Centro"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/equipamento/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdate() throws Exception {
        String equipamentoDTOJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/equipamento/{id}", id)
                        .content(equipamentoDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/equipamento/{id}", id))
                .andExpect(status().isOk());
    }

    private void initClass() {
        objectMapper = new ObjectMapper();

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

        entityList = new ArrayList<>();

        entityList.add(entity);
    }
}
