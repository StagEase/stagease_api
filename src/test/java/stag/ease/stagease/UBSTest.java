package stag.ease.stagease;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stag.ease.stagease.Controller.UBSController;
import stag.ease.stagease.DTO.UBSDTO;
import stag.ease.stagease.Entity.UBSEntity;
import stag.ease.stagease.Repository.UBSRepository;
import stag.ease.stagease.Service.UBSService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UBSTest {
    @Autowired
    private UBSService service;
    @MockBean
    private UBSRepository repository;
    @Mock
    private UBSController controller;

    UBSEntity ubs = new UBSEntity();
    UBSDTO ubsDTO = new UBSDTO();
    List<UBSDTO> ubsDTOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ubs.setId(1L);
        ubs.setNomeUBS("Exemplo ubs");

        ubsDTO.setId(1L);
        ubsDTO.setNomeUBS("Exemplo ubs");

        ubsDTOList.add(ubsDTO);

        when(repository.findById(1L)).thenReturn(Optional.of(ubs));
        when(service.findAll()).thenReturn(ubsDTOList);
        when(controller.findByNomeUBS("Exemplo ubs")).thenReturn(ResponseEntity.ok(ubsDTO));
    }

    @Test
    void testFindByNomeUBS() {
        var ubsDTO = controller.findByNomeUBS("Exemplo ubs");
        String nome = ubsDTO.getBody().getNomeUBS();
        System.out.println(nome);
        Assertions.assertEquals("Exemplo ubs", nome);
    }

    @Test
    void testFindAll() {
        UBSDTO exemploUBSDTO = new UBSDTO();
        exemploUBSDTO.setId(1L);
        exemploUBSDTO.setNomeUBS("Exemplo ubs");

        List<UBSDTO> ubsListFindAll = new ArrayList<>();
        ubsListFindAll.add(exemploUBSDTO);

        when(controller.findAll()).thenReturn(ResponseEntity.ok(ubsListFindAll));

        List<UBSDTO> ubsList = controller.findAll().getBody();
        assertThat(ubsList).isNotNull();
        assertThat(ubsList.size()).isEqualTo(1);
    }

    @Test
    void testCreate() {
        UBSDTO ubsDTO = new UBSDTO();
        ubsDTO.setNomeUBS("Nova ubs");

        when(controller.create(ubsDTO)).thenReturn(ResponseEntity.ok(ubsDTO));

        ResponseEntity<UBSDTO> response = controller.create(ubsDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(ubsDTO);
    }
}
