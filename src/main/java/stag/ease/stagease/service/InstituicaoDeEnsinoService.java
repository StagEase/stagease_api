package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import stag.ease.stagease.dto.InstituicaoDeEnsinoDTO;
import stag.ease.stagease.entity.InstituicaoDeEnsinoEntity;
import stag.ease.stagease.repository.InstituicaoDeEnsinoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstituicaoDeEnsinoService {
    @Autowired
    private InstituicaoDeEnsinoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public InstituicaoDeEnsinoDTO getById(Long id) {
        Optional<InstituicaoDeEnsinoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            InstituicaoDeEnsinoEntity entity = optional.get();
            return modelMapper.map(entity, InstituicaoDeEnsinoDTO.class);
        } else {
            throw new EntityNotFoundException("Área não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public InstituicaoDeEnsinoDTO findByNome(String nome) {
        return modelMapper.map(repository.findByNome(nome), InstituicaoDeEnsinoDTO.class);
    }

    @Transactional
    public List<InstituicaoDeEnsinoDTO> getAll() {
        List<InstituicaoDeEnsinoDTO> list = new ArrayList<>();
        for (InstituicaoDeEnsinoEntity entity : repository.findAll()) {
            InstituicaoDeEnsinoDTO map = modelMapper.map(entity, InstituicaoDeEnsinoDTO.class);
            list.add(map);
        }
        return list;
    }

    @Transactional
    public InstituicaoDeEnsinoDTO create(InstituicaoDeEnsinoDTO dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O id deve ser gerado pelo banco");
        }
        return modelMapper.map(repository.save(modelMapper.map(dto, InstituicaoDeEnsinoEntity.class)), InstituicaoDeEnsinoDTO.class);
    }

    @Transactional
    public InstituicaoDeEnsinoDTO update(Long id, InstituicaoDeEnsinoDTO dto) {
        InstituicaoDeEnsinoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado"));
        modelMapper.map(dto, repository.save(entity));
        return modelMapper.map(entity, InstituicaoDeEnsinoDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        InstituicaoDeEnsinoEntity database = repository.findById(id).orElse(null);

        if (database == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro não encontrado");
        }

        try {
            repository.delete(database);
        } catch (RuntimeException e) {
            if (database.isAtivo()) {
                database.setAtivo(false);
                repository.save(database);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no delete, flag desativada!");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no delete, a flag já está desativada");
        }
    }
}
