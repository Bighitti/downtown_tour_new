package it.unicam.ids.dciotti.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entertainer extends User {
    private final List<Challenge> challenges = new ArrayList<>();

    public Challenge createChallenge(
            String name,
            String description,
            int maxPlayers,
            Date start,
            Date end) {
        Challenge challenge = new Challenge(this);
        challenge.setName(name);
        challenge.setDescription(description);
        challenge.setMaxPlayers(maxPlayers);
        challenge.setStart(start);
        challenge.setEnd(end);
        return challenge;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }
}
