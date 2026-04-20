package com.example.oopjavaprojectscs;

public class Medic extends CrewMember {

    public Medic(int id, String name) {
        super(id, name, "Medic");
    }

    @Override
    public int act() {
        hp += 5;
        if (hp > 100) hp = 100;
        return 5;
    }
}
