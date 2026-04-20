package com.example.oopjavaprojectscs;

public class Pilot extends CrewMember {

    public Pilot(int id, String name) {
        super(id, name,  "Pilot");
    }

    @Override
    public int act() {
        return 15 + experience;
    }
}
