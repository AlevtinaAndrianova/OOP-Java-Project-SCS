package com.example.oopjavaprojectscs;

public class AttackAction implements Action {

    @Override
    public void execute(CrewMember actor, Threat target, StringBuilder log) {
        if (!actor.isAlive() || !target.isAlive()) return;

        int damage = actor.act();
        target.defend(damage);

        log.append(actor.name)
                .append(" attacks ")
                .append(target.name)
                .append(" for ")
                .append(damage)
                .append(" damage.\n");
    }
}

