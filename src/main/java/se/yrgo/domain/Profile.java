package se.yrgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class for Profile
 * with JPA bindings
 *
 * @author Magnus Lilja
 * @author Andreas Karlsson
 */
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String userName;
    private String firstName;
    private String lastName;


    public Profile() {
        // Empty constructor needed for JPA
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
