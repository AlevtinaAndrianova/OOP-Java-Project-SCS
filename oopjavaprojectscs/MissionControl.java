package com.example.oopjavaprojectscs;

import java.util.Arrays;

public class MissionControl {

    private Mission activeMission;
    private int missionCount = 0;
    private final ThreatFactory factory = new ThreatFactory();

    public Mission createMission(CrewMember c1, CrewMember c2) {
        Threat t = factory.generateThreat(missionCount);
        activeMission = new Mission(Arrays.asList(c1, c2), t);
        return activeMission;
    }

    public void launchMission() {
        if (activeMission != null) {
            missionCount++;
            activeMission.start();
        }
    }

    public void endMission() {
        activeMission = null;
    }

    public Mission getActiveMission() {
        return activeMission;
    }

    public int getMissionCount() {
        return missionCount;
    }
}
