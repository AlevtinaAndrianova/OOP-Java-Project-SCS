package com.example.oopjavaprojectscs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private final HashMap<Integer, CrewMember> crewMap = new HashMap<>();
    private int nextId = 1;

    public CrewMember addCrewMember(CrewMember member) {
        member.id = nextId++;
        crewMap.put(member.id, member);
        return member;
    }

    public void removeCrew(int id) {
        crewMap.remove(id);
    }

    public CrewMember getCrew(int id) {
        return crewMap.get(id);
    }

    public List<CrewMember> getAllCrew() {
        return new ArrayList<>(crewMap.values());
    }

    public List<CrewMember> getByLocation(Location location) {
        List<CrewMember> result = new ArrayList<>();
        for (CrewMember m : crewMap.values()) {
            if (m.location == location) result.add(m);
        }
        return result;
    }
}

