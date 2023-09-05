package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.DTO.AreaDTO;
import stag.ease.stagease.DTO.UBSDTO;
import stag.ease.stagease.Entity.AreaEntity;
import stag.ease.stagease.Entity.UBSEntity;
import stag.ease.stagease.Repository.UBSRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UBSService {
    @Autowired
    private UBSRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UBSDTO findByNomeUBS(String nomeUBS) {
        return modelMapper.map(repository.findByNomeUBS(nomeUBS), UBSDTO.class);
    }

    @Transactional
    public List<UBSDTO> list() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, UBSDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UBSDTO create(UBSDTO dto) {
        // Cria uma entidade que recebe os valores da dto da requisição
        // Durante a transformação da entity para dto ele salva a entidade no banco
        // Por fim cria uma dto que recebe os valores da entidade que foi salva
        UBSEntity entity = repository.save(modelMapper.map(dto, UBSEntity.class));
        return new UBSDTO(entity.getId(), entity.isAtivo(), entity.getNomeUBS(),
                entity.getGerente(), entity.getDistrito(), entity.getContatoList(),
                entity.getSupervisorList(), entity.getAreaList(),
                entity.getSolicitacaoList(), entity.getDescricao());
    }

    @Transactional
    public UBSDTO update(Long id, UBSDTO dto) {
        UBSEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, repository.save(entity));

        return new UBSDTO(entity.getId(), entity.isAtivo(), entity.getNomeUBS(),
                entity.getGerente(), entity.getDistrito(), entity.getContatoList(),
                entity.getSupervisorList(), entity.getAreaList(),
                entity.getSolicitacaoList(), entity.getDescricao());
    }
}
