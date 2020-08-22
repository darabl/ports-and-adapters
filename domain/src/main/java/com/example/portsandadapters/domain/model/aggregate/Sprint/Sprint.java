package com.example.portsandadapters.domain.model.aggregate.Sprint;

import com.example.portsandadapters.domain.model.base.AggregateRoot;
import com.example.portsandadapters.domain.model.base.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.Set;

@Setter(AccessLevel.PRIVATE)
@Getter
@SuperBuilder
public class Sprint extends AggregateRoot implements Entity {

    private double sprintCapacityInStoryPoints;

    private final Set<BacklogItem> committedBacklogItems = new LinkedList<>();

    private String name;

    public void commitBacklogItem(BacklogItem backlogItem){
        this.getCommittedBacklogItems().add(backlogItem);
    }

    public double getRemainingStoryPointsCommited() {
        return this.getCommittedBacklogItems().stream()
                                        .mapToDouble(BacklogItem::getRemainingStorypoints)
                                        .sum();
    }



}
