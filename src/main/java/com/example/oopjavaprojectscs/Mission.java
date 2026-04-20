package com.example.oopjavaprojectscs;

import java.io.CharArrayWriter;
import java.util.Iterator;
import java.util.List;

public class Mission {

    private final List<CrewMember> crew;
    private final Threat threat;
    private final TurnManager turnManager;
    private final Storage storage;

    private boolean isActive = false;

    public Mission(List<CrewMember> crew, Threat threat, Storage storage) {

        if (crew == null || threat == null || storage == null) {
            throw new IllegalArgumentException("Mission parameters cannot be null");
        }

        this.crew = crew;
        this.threat = threat;
        this.storage = storage;
        this.turnManager = new TurnManager(crew, threat, storage);
    }

    public void start() {
        isActive = true;

        for (CrewMember m : crew) {
            m.moveTo(Location.ON_MISSION);
        }
    }

    public String executeTurn() {
        if (!isActive) return "Mission is not active.\n";

        String log = turnManager.executeTurn();
        StringBuilder result = new StringBuilder(log);

        if (!turnManager.isThreatAlive()) {
            rewardCrew();
            isActive = false;

            result.append("\nThreat eliminated!\n");
        }

        return result.toString();
    }

    private void rewardCrew() {

        for (CrewMember m : crew) {

            if (m.isAlive()) {
                m.gainExperience(1);
                m.wins++;
                m.moveTo(Location.QUARTERS);
            }
            m.missionsCompleted++;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }
}