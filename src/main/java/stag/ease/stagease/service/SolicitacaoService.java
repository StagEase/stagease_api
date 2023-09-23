package stag.ease.stagease.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.dto.SolicitacaoDTO;
import stag.ease.stagease.entity.SolicitacaoEntity;
import stag.ease.stagease.repository.SolicitacaoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacaoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<SolicitacaoDTO> getList() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, SolicitacaoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public SolicitacaoDTO create(SolicitacaoDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id deve ser gerado pelo banco");
        }
        SolicitacaoEntity entity = repository.save(modelMapper.map(dto, SolicitacaoEntity.class));
        SolicitacaoDTO resultDTO = modelMapper.map(entity, SolicitacaoDTO.class);
        return resultDTO;
    }

    @Transactional
    public SolicitacaoDTO update(Long id, SolicitacaoDTO dto) {
        SolicitacaoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, repository.save(entity));
        return modelMapper.map(entity, SolicitacaoDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        SolicitacaoEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        entity.setAtivo(false);
        repository.save(entity);
    }
}
