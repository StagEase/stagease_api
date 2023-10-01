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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacaoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<SolicitacaoDTO> getList() {
        List<SolicitacaoDTO> listDTO = new ArrayList<>();
        for (SolicitacaoEntity entity : repository.findAll()) {
            SolicitacaoDTO map = modelMapper.map(entity, SolicitacaoDTO.class);
            listDTO.add(map);
        }
        return listDTO;
    }

    @Transactional(readOnly = true)
    public SolicitacaoDTO getById(Long id) {
        Optional<SolicitacaoEntity> optionalSolicitacao = repository.findById(id);
        if (optionalSolicitacao.isPresent()) {
            return modelMapper.map(optionalSolicitacao.get(), SolicitacaoDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encotrado");
        }
    }

    @Transactional
    public SolicitacaoDTO create(SolicitacaoDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id deve ser gerado pelo banco");
        }
        return modelMapper.map(repository.save(modelMapper.map(dto, SolicitacaoEntity.class)), SolicitacaoDTO.class);
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
