package com.example.oopjavaprojectscs;

public class Scientist extends CrewMember {

    public Scientist(int id, String name) {
        super(id, name);
    }

    @Override
    public int act() {
        return 12 + experience;
    }
}

