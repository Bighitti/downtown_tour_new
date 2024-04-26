package it.unicam.ids.dciotti.model;

import java.util.ArrayList;
import java.util.List;

public class Entertainer extends User {
    List<Challenge> challenges = new ArrayList<>();

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }
}
