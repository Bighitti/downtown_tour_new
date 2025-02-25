package it.unicam.ids.dciotti.downtowntour.dto;

import java.util.Date;
import java.util.List;

public class ChallengeDTO {
    private LoginDTO login;
    private Integer id;
    private String name;
    private String description;
    private int maxPlayers;
    private Date start;
    private Date end;
    private boolean open = true;
    private List<ContributorDTO> contributors;
    private UserDTO entertainer;

    public LoginDTO getLogin() {
        return login;
    }

    public void setLogin(LoginDTO login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ContributorDTO> getContributors() {
        return contributors;
    }

    public void setContributors(List<ContributorDTO> contributors) {
        this.contributors = contributors;
    }

    public UserDTO getEntertainer() {
        return entertainer;
    }

    public void setEntertainer(UserDTO entertainer) {
        this.entertainer = entertainer;
    }
}
