package com.example.oopjavaprojectscs;

import java.util.Arrays;

public class MissionControl {

    private Mission activeMission;
    private int missionCount = 0;
    private final ThreatFactory factory = new ThreatFactory();

    private final Storage storage;

    public MissionControl(Storage storage) {
        this.storage = storage;
    }

    public Mission createMission(CrewMember c1, CrewMember c2) {

        Threat t = factory.generateThreat(missionCount);

        GameManager gm = GameManager.getInstance();

        activeMission = new Mission(
                Arrays.asList(c1, c2),
                t,
                gm.storage
        );

        return activeMission;
    }

    public void launchMission() {
        if (activeMission != null) {
            missionCount++;
            activeMission.start();
        }
    }

    public Mission getActiveMission() {
        return activeMission;
    }
}