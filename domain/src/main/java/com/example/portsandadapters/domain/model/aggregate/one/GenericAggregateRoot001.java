package com.example.portsandadapters.domain.model.aggregate.one;

import com.example.portsandadapters.domain.model.base.AggregateRoot;
import com.example.portsandadapters.domain.model.base.Entity;
import com.example.portsandadapters.domain.model.base.IdentifiedObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter
@SuperBuilder
public class GenericAggregateRoot001 extends IdentifiedObject implements AggregateRoot, Entity {

    private final List<GenericEntity001> listOfDependentEntities = new LinkedList<>();

    private String name;

    public void behaviour001(String name, GenericValue001 value) {

        GenericEntity001 genericEntity002 = GenericEntity001.builder()
                                              .name(name)
                                              .referenceValueObject(value)
                                              .build();

        listOfDependentEntities.add(genericEntity002);

    }

}