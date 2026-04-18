package com.example.oopjavaprojectscs;

import java.util.List;

public class GameManager {

    private static GameManager instance;

    public Storage storage;
    public MissionControl missionControl;
    public Simulator simulator;
    public Quarters quarters;
    public StatisticsManager statistics;

    private GameManager() {
        storage = new Storage();
        missionControl = new MissionControl();
        simulator = new Simulator();
        quarters = new Quarters();
        statistics = new StatisticsManager();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void initializeDefaultCrew() {
        if (!storage.getAllCrew().isEmpty()) return;

        storage.addCrewMember(new Pilot(0, "Pilot"));
        storage.addCrewMember(new Engineer(0, "Engineer"));
        storage.addCrewMember(new Medic(0, "Medic"));
        storage.addCrewMember(new Scientist(0, "Scientist"));
        storage.addCrewMember(new Soldier(0, "Soldier"));
    }

    /**
     * Simple demo: runs one mission with the first two crew members
     * and returns the mission log as a String.
     */
    public String runTestMission() {
        List<CrewMember> crew = storage.getAllCrew();
        if (crew.size() < 2) {
            return "Not enough crew members to run a mission.\n";
        }

        Mission mission = missionControl.createMission(crew.get(0), crew.get(1));
        missionControl.launchMission();

        StringBuilder log = new StringBuilder();
        log.append("Starting test mission with ")
                .append(crew.get(0).name)
                .append(" and ")
                .append(crew.get(1).name)
                .append(".\n\n");

        while (mission.isActive()) {
            log.append(mission.executeTurn());
            log.append("\n");
        }

        log.append("Mission finished.\n");
        return log.toString();
    }
}
