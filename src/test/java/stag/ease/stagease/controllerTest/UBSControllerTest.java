package stag.ease.stagease.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import stag.ease.stagease.controller.UBSController;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.dto.UBSDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.entity.SupervisorEntity;
import stag.ease.stagease.entity.UBSEntity;
import stag.ease.stagease.entity.enums.Distrito;
import stag.ease.stagease.repository.UBSRepository;
import stag.ease.stagease.service.UBSService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UBSControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private UBSController controller;
    @Mock
    private UBSService service;
    @Mock
    private UBSRepository repository;
    private ObjectMapper objectMapper;
    private UBSDTO dto;
    private UBSEntity entity;
    private List<UBSEntity> entityList;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        initClass();

        when(service.getById(anyLong())).thenReturn(entity);
        when(service.getByNomeUBS(anyString())).thenReturn(entity);
        when(service.getAll()).thenReturn(entityList);
        when(service.create(any(UBSEntity.class))).thenReturn(entity);
        when(service.update(anyLong(), any(UBSEntity.class))).thenReturn(entity);
        doNothing().when(service).deleteById(anyLong());
    }
    /*
    @Test
    void testGetById() throws Exception {
    }

    @Test
    void testGetByNomeUBS() throws Exception {
    }

    @Test
    void testGetAll() throws Exception {
    }

    @Test
    void testCreate() throws Exception {
    }

    @Test
    void testUpdate() throws Exception {
    }
    */
    @Test
    void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/ubs/{id}", id))
                .andExpect(status().isOk());
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

        entityList = new ArrayList<>();

        entityList.add(entity);
    }
}
