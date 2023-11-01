package stag.ease.stagease.service;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stag.ease.stagease.config.InvalidDateTimeException;
import stag.ease.stagease.entity.SolicitacaoEntity;
import stag.ease.stagease.repository.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoService {
    @Autowired
    private SolicitacaoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<SolicitacaoEntity> getAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public SolicitacaoEntity getById(Long id) {
        Optional<SolicitacaoEntity> optional = this.repository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            throw new EntityNotFoundException("Solicitação não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public SolicitacaoEntity create(SolicitacaoEntity entity) {
        validaData(entity);
        validaHora(entity);
        return this.repository.save(entity);
    }

    @Transactional
    public SolicitacaoEntity update(Long id, SolicitacaoEntity entity) {
        SolicitacaoEntity database = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Área não encontrada com o ID: " + id));
        validaData(entity);
        validaHora(entity);

        modelMapper.map(entity, database);
        return repository.save(database);
    }

    @Transactional
    public void delete(Long id) {
        SolicitacaoEntity entity = repository.findById(id).orElseThrow(() -> new  EntityNotFoundException("Não foi possível encontrar o registro informado"));
        entity.setAtivo(false);
        repository.save(entity);
    }

    private void validaData(SolicitacaoEntity solicitacao) {
        if (solicitacao.getDataInicio().isAfter(solicitacao.getDataFim())) {
            throw new InvalidDateTimeException("A data de início não pode ser posterior à data final");
        }
    }

    private void validaHora(SolicitacaoEntity solicitacao) {
        if (solicitacao.getInicioExpediente().isAfter(solicitacao.getFimExpediente())) {
            throw new InvalidDateTimeException("O início do expediente não pode ser posterior ao final do expediente");
        }
    }
}
