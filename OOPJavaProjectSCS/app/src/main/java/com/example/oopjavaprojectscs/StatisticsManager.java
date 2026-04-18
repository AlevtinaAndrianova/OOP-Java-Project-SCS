package com.example.oopjavaprojectscs;

public class StatisticsManager {

    public void recordMission(CrewMember member, boolean win) {
        member.missionsCompleted++;
        if (win) member.wins++;
    }

    public void recordTraining(CrewMember member) {
        member.trainings++;
    }
}
