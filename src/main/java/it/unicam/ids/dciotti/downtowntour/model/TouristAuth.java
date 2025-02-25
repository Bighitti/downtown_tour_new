package it.unicam.ids.dciotti.downtowntour.model;

import java.util.Date;

public class TouristAuth extends Tourist {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String phone;

    public TouristAuth(Coordinates lastCoords) {
        super(lastCoords);
    }

    public void report(Content content) {
        new Report(this, content);
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}