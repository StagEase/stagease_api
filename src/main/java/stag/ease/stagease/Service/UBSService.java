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
        //Busca uma entidade no repositório de acordo com o nome informado
        //Em seguida cria uma dto de acordo do dados da entidade
        return modelMapper.map(repository.findByNomeUBS(nomeUBS), UBSDTO.class);
    }

    @Transactional
    public List<UBSDTO> list() {
        //Busca todas entidades no repositório
        //Em seguida, tranforma cada uma delas em dto de acordo com os dados da entidade
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, UBSDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public UBSDTO create(UBSDTO dto) {
        // Cria uma entidade que recebe os valores da dto da requisição
        // Durante a transformação da entity para dto ele salva a entidade no banco
        // Por fim cria uma dto que recebe os valores da entidade que foi salva
        return modelMapper.map(repository.save(modelMapper.map(dto, UBSEntity.class)), UBSDTO.class);
    }

    @Transactional
    public UBSDTO update(Long id, UBSDTO dto) {
        // Cria uma entidade que recebe o retorno da busca do id no repositório
        // Retorna erro caso nada for encontrado
        UBSEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o registro informado"));
        // Copia os dados da dto recebida para a entidade, ao mesmo tempo que salva a entidade
        modelMapper.map(dto, repository.save(entity));

        //Retorna uma dto que recebe os valores da entidade que foi salva
        return modelMapper.map(entity, UBSDTO.class);
    }
}
