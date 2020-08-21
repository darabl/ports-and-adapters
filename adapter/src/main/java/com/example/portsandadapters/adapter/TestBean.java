package com.example.portsandadapters.adapter;

import com.example.portsandadapters.application.port.in.command.CommandAbstraction;
import com.example.portsandadapters.application.port.in.command.HandlerAbstraction;
import com.example.portsandadapters.application.domainstories.command.sprint.commitbacklogitem.CommandSprintCommitBacklogItem;
import com.example.portsandadapters.application.domainstories.command.sprint.commitbacklogitem.HandlerSprintCommitBacklogItem;
import com.example.portsandadapters.application.port.out.persistence.PersistenceAbstraction;
import com.example.portsandadapters.domain.model.aggregate.Sprint.BacklogItem;
import com.example.portsandadapters.domain.model.aggregate.Sprint.Sprint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestBean {

    private final PersistenceAbstraction<Sprint, Long> sprintPersistenceAbstraction;


    public void testcrudservices() {
        Sprint sprint1 = Sprint.builder()
                               .name("Testsprint 01")
                               .id(1L)
                               .build();

        Sprint sprint2 = Sprint.builder()
                               .name("Testsprint 02")
                               .id(2L)
                               .build();

        Sprint sprint3 = Sprint.builder()
                               .name("Testsprint 03")
                               .id(3L)
                               .build();

        BacklogItem bli1 = BacklogItem.builder()
                                      .name("bli 1")
                                      .remainingStorypoints(1.1)
                                      .build();

        BacklogItem bli2 = BacklogItem.builder()
                                      .name("bli 1")
                                      .remainingStorypoints(2.2)
                                      .build();

        BacklogItem bli3 = BacklogItem.builder()
                                      .name("bli 1")
                                      .remainingStorypoints(3.3)
                                      .build();

        sprintPersistenceAbstraction.save(sprint1.getId(), sprint1);
        sprintPersistenceAbstraction.save(sprint2.getId(), sprint2);
        sprintPersistenceAbstraction.save(sprint3.getId(), sprint3);


        HandlerAbstraction handlerAbstraction = new HandlerSprintCommitBacklogItem(sprintPersistenceAbstraction);
        CommandAbstraction command = CommandSprintCommitBacklogItem.builder()
                                                                   .backlogItemid(1L)
                                                                   .sprintId(1L)
                                                                   .build();

        System.out.println("wert 1: " + sprintPersistenceAbstraction.findByID(1L)
                                                                      .getRemainingStoryPointsCommited());
        handlerAbstraction.handle(command);

        System.out.println("wert 2: " + sprintPersistenceAbstraction.findByID(1L)
                                                                                .getRemainingStoryPointsCommited());

    }

}