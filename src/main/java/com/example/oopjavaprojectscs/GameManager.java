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
        missionControl = new MissionControl(storage);
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

        storage.addCrewMember(new Pilot(0, "A"));
        storage.addCrewMember(new Engineer(0, "B"));
        storage.addCrewMember(new Medic(0, "C"));
        storage.addCrewMember(new Scientist(0, "D"));
        storage.addCrewMember(new Soldier(0, "E"));
    }
}