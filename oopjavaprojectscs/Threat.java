package com.example.oopjavaprojectscs;

import java.util.Random;

public class Threat {

    public String name;
    public int hp;
    public int attackPower;
    public ThreatType type;

    private final Random random = new Random();

    public Threat(String name, int hp, int attackPower, ThreatType type) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        this.type = type;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void defend(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public int act() {
        return attackPower / 2 + random.nextInt(attackPower / 2 + 1);
    }
}
