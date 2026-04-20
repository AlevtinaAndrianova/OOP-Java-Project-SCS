package com.example.oopjavaprojectscs;

public abstract class CrewMember {

    public int id;

    public String name;
    public String occupation ;
    public int hp;
    public int energy;
    public int experience;
    public int wins;
    public int missionsCompleted;
    public int trainings;
    public Location location;

    public CrewMember(int id, String name, String occupation) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.hp = 100;
        this.energy = 100;
        this.experience = 0;
        this.wins = 0;
        this.missionsCompleted = 0;
        this.trainings = 0;
        this.location = location;

    }
    public boolean isAlive() {
        return hp > 0;
    }

    public void moveTo(Location location) {
        this.location = location;
    }

    public Location getLocation(){
        return location;
    }

    public void gainExperience(int amount) {
        experience += amount;
    }

    public void restoreEnergy() {
        energy = 100;
    }

    public void defend(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public abstract int act();
}

