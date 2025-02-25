package it.unicam.ids.dciotti.downtowntour.entity;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(
        name = "users",
        schema = "downtown_tour",
        uniqueConstraints = {
                @UniqueConstraint(name = "USER_UNIQUE_TAX", columnNames = { "fiscalCode" }),
                @UniqueConstraint(name = "USER_UNIQUE_EMAIL", columnNames = { "email" }),
                @UniqueConstraint(name = "USER_UNIQUE_PHONE", columnNames = { "phone" })
        }
)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "firstname", length = 64, nullable = false)
    private String firstname;
    @Column(name = "lastname", length = 64, nullable = false)
    private String lastname;
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Column(name = "email", length = 320, nullable = false)
    private String email;
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    @Column(name = "phone", length = 25, nullable = true)
    private String phone;
    @Column(name = "address", length = 255, nullable = true)
    private String address;
    @Column(name = "fiscal_code", length = 16, nullable = false)
    private String fiscalCode;
    @Column(name = "privilege_csv", length = 50, nullable = true)
    private String privilegeCSV;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getPrivilegeCSV() {
        return privilegeCSV;
    }

    public void setPrivilegeCSV(String privilegeCSV) {
        this.privilegeCSV = privilegeCSV;
    }
}
