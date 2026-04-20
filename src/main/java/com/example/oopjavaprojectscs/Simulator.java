package com.example.oopjavaprojectscs;

import java.util.List;
import java.util.Random;

public class Simulator {

    public void train(CrewMember member) {
        member.moveTo(Location.SIMULATOR);
        Random rand = new Random();
        new android.os.Handler().postDelayed(() -> {
            member.gainExperience(rand.nextInt(10) + 1);
            member.trainings++;
        }, 500*rand.nextInt(5)+1);// The timer is supposed to be longer in real life
    }

    public void trainAll(List<CrewMember> list) {
        for (CrewMember m : list) {
            train(m);
        }
    }
}

