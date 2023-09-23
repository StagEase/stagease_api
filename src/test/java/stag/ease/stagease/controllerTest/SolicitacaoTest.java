package stag.ease.stagease.controllerTest;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stag.ease.stagease.controller.SolicitacaoController;
import stag.ease.stagease.dto.SolicitacaoDTO;
import stag.ease.stagease.entity.enums.Situacao;
import stag.ease.stagease.entity.SolicitacaoEntity;
import stag.ease.stagease.repository.SolicitacaoRepository;
import stag.ease.stagease.service.SolicitacaoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class SolicitacaoTest {
    SolicitacaoEntity solicitacao = new SolicitacaoEntity();
    SolicitacaoDTO dto = new SolicitacaoDTO();
    List<SolicitacaoDTO> dtoList = new ArrayList<>();
    @Autowired
    private SolicitacaoService service;
    @MockBean
    private SolicitacaoRepository repository;
    @Mock
    private SolicitacaoController controller;

    @BeforeEach
    void inject() {
        MockitoAnnotations.openMocks(this);

        solicitacao.setId(1L);
        solicitacao.setDataInicio(LocalDate.now());
        solicitacao.setDataFim(LocalDate.now());
        solicitacao.setInicioExpediente(LocalTime.now());
        solicitacao.setFimExpediente(LocalTime.now());
        solicitacao.setSituacao(Situacao.LIBERADO);

        dto.setId(1L);
        dto.setDataInicio(LocalDate.now());
        dto.setDataFim(LocalDate.now());
        dto.setInicioExpediente(LocalTime.now());
        dto.setFimExpediente(LocalTime.now());
        dto.setSituacao(Situacao.LIBERADO);

        dtoList.add(dto);

        when(repository.findById(1L)).thenReturn(Optional.of(solicitacao));
        when(service.getList()).thenReturn(dtoList);
    }

    @Test
    void testFindAll() {
        SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();
        solicitacaoDTO.setId(1L);
        solicitacaoDTO.setDataInicio(LocalDate.now());
        solicitacaoDTO.setDataFim(LocalDate.now());
        solicitacaoDTO.setInicioExpediente(LocalTime.now());
        solicitacaoDTO.setFimExpediente(LocalTime.now());
        solicitacaoDTO.setSituacao(Situacao.LIBERADO);

        List<SolicitacaoDTO> findList = new ArrayList<>();
        findList.add(solicitacaoDTO);

        when(controller.list()).thenReturn(findList);

        List<SolicitacaoDTO> solicitacaoDTOS = controller.list();
        ListAssert<SolicitacaoDTO> notNull = Assertions.assertThat(solicitacaoDTOS).isNotNull();
        Assertions.assertThat(solicitacaoDTOS).hasSize(1);
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
}
