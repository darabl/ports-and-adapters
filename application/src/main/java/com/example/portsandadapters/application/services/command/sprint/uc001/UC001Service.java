package com.example.portsandadapters.application.services.command.sprint.uc001;

import com.example.portsandadapters.domain.aggregates.one.GenericAggregateRoot001;
import com.example.portsandadapters.domain.aggregates.one.GenericValue001;
import com.example.portsandadapters.domain.aggregates.two.GenericAggregateRoot002;
import com.example.portsandadapters.domain.services.OneTwoService;
import com.example.portsandadapters.ports.inbound.command.CommandHandler;
import com.example.portsandadapters.ports.outbound.persistence.PersistenceAbstraction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UC001Service extends CommandHandler<UC001Input, UC001Output> {

    @NonNull PersistenceAbstraction<GenericAggregateRoot001, Long> repo001;

    @NonNull PersistenceAbstraction<GenericAggregateRoot002, Long> repo002;

    @NonNull private OneTwoService oneTwoService;

    @Override
    public UC001Output process(UC001Input input) {

        log.info("setup of example repos");

        Long aggOneRef = 0L;
        Long aggTwoRef = 0L;

        GenericAggregateRoot001 agg01 = GenericAggregateRoot001.builder().build();
        GenericAggregateRoot002 agg02 = GenericAggregateRoot002.builder().build();

        repo001.save(agg01);
        repo002.save(agg02);

        GenericAggregateRoot001 ag001 = repo001.findByID(aggOneRef);
        GenericAggregateRoot002 ag002 = repo002.findByID(aggTwoRef);

        ag001.behaviour001("one", GenericValue001.ONE);
        ag001.behaviour001("two", GenericValue001.TWO);
        ag001.behaviour001("three", GenericValue001.THREE);
        ag001.behaviour001("four", GenericValue001.FOUR);
        ag001.behaviour001("five", GenericValue001.FIVE);
        repo001.save(ag001);

        log.info("processing usecase 1");

        oneTwoService.doSomethingInvolvingBothAggregates(aggOneRef, aggTwoRef, 1);

        UC001Output output = new UC001Output();
        return output;
    }

}
