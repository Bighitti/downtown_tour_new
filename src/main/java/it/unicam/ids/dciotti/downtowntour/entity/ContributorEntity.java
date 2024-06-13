package it.unicam.ids.dciotti.downtowntour.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(
        name = "contributor",
        schema = "downtown_tour",
        uniqueConstraints = {
                @UniqueConstraint(name = "CONTRIBUTOR_UNIQUE_TAX", columnNames = { "fiscalCode" }),
                @UniqueConstraint(name = "CONTRIBUTOR_UNIQUE_EMAIL", columnNames = { "email" })
        }
)
public class ContributorEntity {
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
    @Column(name = "phone", length = 25, nullable = true)
    private String phone;
    @Column(name = "address", length = 255, nullable = true)
    private String address;
    @Column(name = "fiscal_code", length = 16, nullable = false)
    private String fiscalCode;
    @Column(name = "curator", nullable = false)
    private Boolean curator = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorized_by", nullable = true)
    private ContributorEntity authorizedBy;

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

    public Boolean getCurator() {
        return curator;
    }

    public void setCurator(Boolean curator) {
        this.curator = curator;
    }

    public ContributorEntity getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(ContributorEntity authorizedBy) {
        this.authorizedBy = authorizedBy;
    }
}
