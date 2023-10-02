package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return modelMapper.map(repository.save(modelMapper.map(dto, InstituicaoDeEnsinoEntity.class)), InstituicaoDeEnsinoDTO.class);
    }

    @Transactional
    public InstituicaoDeEnsinoDTO update(Long id, InstituicaoDeEnsinoDTO dto) {
        InstituicaoDeEnsinoEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Área não encontrada com o ID: " + id));

        modelMapper.map(dto, existingEntity);

        return modelMapper.map(repository.save(existingEntity), InstituicaoDeEnsinoDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
