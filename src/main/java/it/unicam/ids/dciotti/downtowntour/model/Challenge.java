package it.unicam.ids.dciotti.downtowntour.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Challenge {
    private String name;
    private String description;
    private int maxPlayers;
    private Date start;
    private Date end;
    private boolean open = true;
    private final List<Content> contents = new ArrayList<>();
    private final List<Contributor> contributors = new ArrayList<>();
    private Entertainer owner;

    public Challenge(Entertainer owner) {
        this.owner = owner;
        if (owner != null) {
            owner.getChallenges().add(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Content> getContents() {
        return contents;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public Entertainer getOwner() {
        return owner;
    }

    public void setOwner(Entertainer owner) {
        if (this.owner != null) {
            this.owner.getChallenges().remove(this);
        }
        this.owner = owner;
        if (this.owner != null) {
            this.owner.getChallenges().add(this);
        }
    }
}
