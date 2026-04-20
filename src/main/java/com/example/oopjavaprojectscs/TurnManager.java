package com.example.oopjavaprojectscs;

import java.util.Iterator;
import java.util.List;

public class TurnManager {

    private final List<CrewMember> crew;
    private final Threat threat;
    private final Storage storage;

    public TurnManager(List<CrewMember> crew, Threat threat, Storage storage) {
        this.crew = crew;
        this.threat = threat;
        this.storage = storage;
    }

    public String executeTurn() {

        StringBuilder log = new StringBuilder();

        log.append("Threat HP: ")
                .append(threat.getHp())
                .append("\n\n");

        Iterator<CrewMember> it = crew.iterator();

        while (it.hasNext()) {
            CrewMember m = it.next();

            if (!m.isAlive()) continue;

            int damage = m.act(); // or any attack logic you have
            threat.defend(damage);

            log.append(m.getName())
                    .append(" deals ")
                    .append(damage)
                    .append(" damage to threat. Threat HP: ")
                    .append(threat.getHp())
                    .append("\n");


            int incoming = threat.attack();
            m.setHp(m.getHp() - incoming);

            log.append(m.getName())
                    .append(" takes ")
                    .append(incoming)
                    .append(" damage. HP: ")
                    .append(m.getHp())
                    .append("\n");

            if (m.getHp() <= 0) {
                m.setHp(0);
                storage.removeCrew(m.getId());
                it.remove();

                log.append(m.getName())
                        .append(" has died!\n");
            }

            log.append("\n");
        }

        log.append("Threat HP after turn: ")
                .append(threat.getHp())
                .append("\n");

        return log.toString();
    }

    public boolean isThreatAlive() {
        return threat.isAlive();
    }
}