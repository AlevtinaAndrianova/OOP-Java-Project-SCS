package com.example.oopjavaprojectscs;

public class Engineer extends CrewMember {

    public Engineer(int id, String name) {
        super(id, name);
    }

    @Override
    public int act() {
        return 10 + experience;
    }
}

