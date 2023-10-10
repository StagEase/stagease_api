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
import stag.ease.stagease.controller.AreaController;
import stag.ease.stagease.dto.AreaDTO;
import stag.ease.stagease.entity.AreaEntity;
import stag.ease.stagease.service.AreaService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AreaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private AreaController controller;
    @Mock
    private AreaService service;
    @Mock
    private ModelMapper modelMapper;
    private final Long id = 1L;
    private AreaDTO dto;
    private AreaEntity entity;
    private List<AreaEntity> entityList;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        dto = new AreaDTO();
        dto.setId(id);
        dto.setNomeArea("Enfermagem");

        entity = new AreaEntity();
        entity.setId(id);
        entity.setNomeArea("Enfermagem");

        entityList = new ArrayList<>();
        entityList.add(entity);
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
        mockMvc.perform(get("/area/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetByNomeArea() throws Exception {
        when(service.getByNomeArea("Enfermagem")).thenReturn(entity);
        mockMvc.perform(get("/area/nome/{nome}", "Enfermagem"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAll() throws Exception {
        when(service.getAll()).thenReturn(entityList);
        mockMvc.perform(get("/area/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldUpdate() throws Exception {
        String areaDTOJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/area/{id}", id)
                        .content(areaDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/area/{id}", id))
                .andExpect(status().isOk());
    }
}