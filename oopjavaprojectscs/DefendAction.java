package com.example.oopjavaprojectscs;

public class DefendAction implements Action {

    @Override
    public void execute(CrewMember actor, Threat target, StringBuilder log) {
        if (!actor.isAlive() || !target.isAlive()) return;

        int retaliation = target.act();
        actor.defend(retaliation);

        log.append(target.name)
                .append(" hits ")
                .append(actor.name)
                .append(" for ")
                .append(retaliation)
                .append(" damage.\n");
    }
}

