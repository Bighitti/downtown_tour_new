package it.unicam.ids.dciotti.downtowntour.dto;

public class ContributorDTO {
    private Integer id;
    private UserDTO user;
    private CuratorDTO curator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CuratorDTO getCurator() {
        return curator;
    }

    public void setCurator(CuratorDTO curator) {
        this.curator = curator;
    }

    public boolean curator() {
        return false;
    }
}
