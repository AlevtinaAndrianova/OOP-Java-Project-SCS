package com.example.oopjavaprojectscs;

import java.util.List;

public class Mission {

    private final List<CrewMember> crew;
    private final Threat threat;
    private final TurnManager turnManager;

    private boolean isActive = false;

    public Mission(List<CrewMember> crew, Threat threat) {
        this.crew = crew;
        this.threat = threat;
        this.turnManager = new TurnManager(crew, threat);
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

        if (!turnManager.isThreatAlive()) {
            rewardCrew();
            isActive = false;
        }

        return log;
    }

    private void rewardCrew() {
        for (CrewMember m : crew) {
            if (m.isAlive()) {
                m.gainExperience(1);
                m.wins++;
            }
            m.missionsCompleted++;
            m.moveTo(Location.MISSION_CONTROL);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }
}

