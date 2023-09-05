package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Repository.AreaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService {
    @Autowired
    private AreaRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AreaDTO findByNomeArea(String nomeArea) {
        return modelMapper.map(repository.findByNomeArea(nomeArea), AreaDTO.class);
    }

    @Transactional
    public List<AreaDTO> list() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, AreaDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public AreaDTO create(AreaDTO dto) {
        // Cria uma entidade que recebe os valores da dto da requisição
        // Durante a transformação da entity para dto ele salva a entidade no banco
        // Por fim cria uma dto que recebe os valores da entidade que foi salva
        AreaEntity entity = repository.save(modelMapper.map(dto, AreaEntity.class));
        return new AreaDTO(entity.getId(), entity.isAtivo(), entity.getNomeArea(), entity.getUbsList(), entity.getSolicitacaoList());
    }

    @Transactional
    public AreaDTO update(Long id, AreaDTO dto) {
        AreaEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, repository.save(entity));

        return new AreaDTO(entity.getId(), entity.isAtivo(), entity.getNomeArea(), entity.getUbsList(), entity.getSolicitacaoList());
    }
}
