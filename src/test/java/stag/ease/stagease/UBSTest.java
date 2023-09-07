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


}
