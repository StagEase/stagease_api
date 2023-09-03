package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.SolicitacaoDTO;
import stag.ease.stagease.Entity.SolicitacaoEntity;
import stag.ease.stagease.Repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public SolicitacaoService(SolicitacaoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public SolicitacaoEntity create(SolicitacaoDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id deve ser gerado pelo banco");
        }
        SolicitacaoEntity entity = modelMapper.map(dto, SolicitacaoEntity.class);
        repository.save(entity);
        return entity;
    }

    @Transactional
    public SolicitacaoEntity update(Long id, SolicitacaoDTO dto) {
        SolicitacaoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, entity);
        repository.save(entity);
        return entity;
    }
}
