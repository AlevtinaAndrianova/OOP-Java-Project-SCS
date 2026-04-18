package com.example.oopjavaprojectscs;

import java.util.List;

public class Quarters {

    public void enter(CrewMember member) {
        member.restoreEnergy();
        member.moveTo(Location.QUARTERS);
    }

    public void restoreAll(List<CrewMember> crew) {
        for (CrewMember m : crew) {
            enter(m);
        }
    }
}

