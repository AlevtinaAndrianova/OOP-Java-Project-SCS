package com.example.oopjavaprojectscs;

import java.util.List;

public class Quarters {

    public void enter(CrewMember member) {
        if(member.getLocation() == Location.QUARTERS)
            member.restoreEnergy();
    }

    public void restoreAll(List<CrewMember> crew) {
        for (CrewMember m : crew) {
            enter(m);
        }
    }
}

