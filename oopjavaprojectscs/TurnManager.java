package com.example.oopjavaprojectscs;

import java.util.List;

public class TurnManager {

    private final List<CrewMember> crew;
    private final Threat threat;

    private final Action attackAction = new AttackAction();
    private final Action defendAction = new DefendAction();

    public TurnManager(List<CrewMember> crew, Threat threat) {
        this.crew = crew;
        this.threat = threat;
    }

    public String executeTurn() {
        StringBuilder log = new StringBuilder();

        for (CrewMember member : crew) {
            if (!member.isAlive() || !threat.isAlive()) continue;

            attackAction.execute(member, threat, log);

            if (!threat.isAlive()) {
                log.append("Threat defeated: ")
                        .append(threat.name)
                        .append("!\n");
                break;
            }

            defendAction.execute(member, threat, log);
        }

        return log.toString();
    }

    public boolean isThreatAlive() {
        return threat.isAlive();
    }
}

