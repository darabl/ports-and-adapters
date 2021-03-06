package com.example.portsandadapters.configuration;

import com.example.portsandadapters.application.services.command.sprint.uc001.UC001Service;
import com.example.portsandadapters.domain.aggregates.one.GenericAggregateRoot001;
import com.example.portsandadapters.ports.outbound.persistence.PersistenceAbstraction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:command.properties")
@RequiredArgsConstructor
public class CommandConfig {

    @NonNull private PersistenceAbstraction<GenericAggregateRoot001, Long> ag001Repo;

    @Bean
    public UC001Service Ag001_Com001_LongName_Service() {
        return null;//new UC001Service(ag001Repo);
    }

}
