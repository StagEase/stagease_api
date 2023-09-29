package stag.ease.stagease.controllerTest;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import stag.ease.stagease.controller.SolicitacaoController;
import stag.ease.stagease.dto.SolicitacaoDTO;
import stag.ease.stagease.entity.enums.Situacao;
import stag.ease.stagease.repository.SolicitacaoRepository;
import stag.ease.stagease.service.SolicitacaoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class SolicitacaoControllerTest {
    @InjectMocks
    private SolicitacaoController controller;
    @Mock
    private SolicitacaoService service;
    @Mock
    private SolicitacaoRepository repository;
    @Mock
    private ModelMapper modelMapper;
    private SolicitacaoDTO dto;
    private List<SolicitacaoDTO> listDTO;
    private final Long id = 1L;

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
    void testCreate(){
        SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();
        solicitacaoDTO.setQntdEstagiarios(7);

        when(controller.create(solicitacaoDTO)).thenReturn(ResponseEntity.ok(solicitacaoDTO));

        ResponseEntity<SolicitacaoDTO> response = controller.create(solicitacaoDTO);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(solicitacaoDTO);
    }

    private void initClass() {
        dto.setId(id);
        dto.setDataInicio(LocalDate.now());
        dto.setDataFim(LocalDate.now());
        dto.setInicioExpediente(LocalTime.now());
        dto.setFimExpediente(LocalTime.now());
        dto.setSituacao(Situacao.LIBERADO);

        listDTO.add(dto);
    }
}
