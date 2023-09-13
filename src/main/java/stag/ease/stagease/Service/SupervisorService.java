package stag.ease.stagease.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.DTO.SupervisorDTO;
import stag.ease.stagease.DTO.UBSDTO;
import stag.ease.stagease.Entity.SupervisorEntity;
import stag.ease.stagease.Repository.SupervisorRepository;

@Service
public class SupervisorService {
    @Autowired
    private SupervisorRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public SupervisorDTO findByNomeSupervisor(String nomeSupervisor) {
        return modelMapper.map(repository.findByNomeSupervisor(nomeSupervisor), SupervisorDTO.class);
    }

    @Transactional
    public SupervisorDTO create(SupervisorDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id deve ser gerado pelo banco");
        }
        SupervisorEntity entity = repository.save(modelMapper.map(dto, SupervisorEntity.class));
        SupervisorDTO resultDTO = modelMapper.map(entity, SupervisorDTO.class);
        return resultDTO;
    }

    @Transactional
    public SupervisorDTO update(Long id, SupervisorDTO dto) {
        SupervisorEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, repository.save(entity));
        return modelMapper.map(entity, SupervisorDTO.class);
    }
}
