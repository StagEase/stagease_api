package stag.ease.stagease.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stag.ease.stagease.DTO.AbstractDTO;
import stag.ease.stagease.Entity.AbstractEntity;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        // Configuração de mapeamento personalizado para AbstractEntity e AbstractDTO
        modelMapper.typeMap(AbstractEntity.class, AbstractDTO.class)
                .addMapping(AbstractEntity::getId, AbstractDTO::setId);
        return  modelMapper;
    }
}
