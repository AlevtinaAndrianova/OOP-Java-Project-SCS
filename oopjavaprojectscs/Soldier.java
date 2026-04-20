package com.example.oopjavaprojectscs;

public class Soldier extends CrewMember {

    public Soldier(int id, String name) {
        super(id, name,  "Soldier");
    }

    @Override
    public int act() {
        return 20 + experience;
    }
}

