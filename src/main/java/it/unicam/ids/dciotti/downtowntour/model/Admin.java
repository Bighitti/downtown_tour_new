package it.unicam.ids.dciotti.downtowntour.model;

public class Admin extends User {
    private String privilegesCSV;

    public String getPrivilegesCSV() {
        return privilegesCSV;
    }

    public void setPrivilegesCSV(String privilegesCSV) {
        this.privilegesCSV = privilegesCSV;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "firstname='" + getFirstname() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                ", birthday=" + getBirthday() +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", fiscalCode='" + getFiscalCode() + '\'' +
                ", privilegesCSV='" + privilegesCSV + '\'' +
                '}';
    }
}
