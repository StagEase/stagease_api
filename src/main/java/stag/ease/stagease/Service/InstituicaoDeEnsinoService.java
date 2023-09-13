package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.DTO.SupervisorDTO;
import stag.ease.stagease.Entity.InstituicaoDeEnsinoEntity;
import stag.ease.stagease.Repository.InstituicaoDeEnsinoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstituicaoDeEnsinoService {
    @Autowired
    private InstituicaoDeEnsinoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<InstituicaoDeEnsinoDTO> getList() {
        return repository.findAll().stream()
                .map(entity -> modelMapper.map(entity, InstituicaoDeEnsinoDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public InstituicaoDeEnsinoDTO create(InstituicaoDeEnsinoDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id deve ser gerado pelo banco");
        }
        InstituicaoDeEnsinoEntity entity = repository.save(modelMapper.map(dto, InstituicaoDeEnsinoEntity.class));
        InstituicaoDeEnsinoDTO resultDTO = modelMapper.map(entity, InstituicaoDeEnsinoDTO.class);
        return resultDTO;
    }

    @Transactional
    public InstituicaoDeEnsinoDTO update(Long id, InstituicaoDeEnsinoDTO dto) {
        InstituicaoDeEnsinoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, repository.save(entity));
        return modelMapper.map(entity, InstituicaoDeEnsinoDTO.class);
    }
}
