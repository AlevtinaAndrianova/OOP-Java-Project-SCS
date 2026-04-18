package com.example.oopjavaprojectscs;

public class ThreatFactory {

    public Threat generateThreat(int missionIndex) {
        int level = missionIndex + 1;
        int hp = 50 + level * 10;
        int attack = 10 + level * 5;

        if (missionIndex % 3 == 0) {
            return new Threat("Alien Raider", hp, attack, ThreatType.ALIEN);
        } else if (missionIndex % 3 == 1) {
            return new Threat("Asteroid Field", hp, attack, ThreatType.ASTEROID);
        } else {
            return new Threat("System Failure", hp, attack, ThreatType.SYSTEM_FAILURE);
        }
    }
}

