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
import stag.ease.stagease.entity.SupervisorEntity;
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
    public InstituicaoDeEnsinoEntity getById(Long id) {
        Optional<InstituicaoDeEnsinoEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Instituicao não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public InstituicaoDeEnsinoEntity findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Transactional
    public List<InstituicaoDeEnsinoEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public InstituicaoDeEnsinoEntity create(InstituicaoDeEnsinoEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public InstituicaoDeEnsinoEntity update(Long id, InstituicaoDeEnsinoEntity entity) {
        InstituicaoDeEnsinoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instituicao não encontrado com o ID: " + id));
        modelMapper.map(entity, existingEntity);
        return repository.save(existingEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        InstituicaoDeEnsinoEntity database = repository.findById(id).orElse(null);

        if (database == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o registro informado");
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
