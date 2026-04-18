package com.example.oopjavaprojectscs;

import java.util.List;

public class Simulator {

    public void train(CrewMember member) {
        member.gainExperience(1);
        member.trainings++;
        member.moveTo(Location.SIMULATOR);
    }

    public void trainAll(List<CrewMember> list) {
        for (CrewMember m : list) {
            train(m);
        }
    }
}

