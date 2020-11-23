package com.tyler.classroom;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User class intended to represent the users table
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    @JsonProperty("id")
    private Long id;

    @Column(name="first_name")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name="last_name")
    @JsonProperty("lastName")
    private String lastName;

    @Column(name="middle_name")
    @JsonProperty("middleName")
    private String middleName;

    @Column(name="is_instructor")
    @JsonProperty("isInstructor")
    private boolean isInstructor;

    @Column(name="username")
    @JsonProperty("username")
    private String username;

    @Column(name="password")
    @JsonProperty("password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "sessions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id"))
    @JsonProperty("classrooms")
    private Set<Classroom> classrooms;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public boolean isInstructor() {
        return isInstructor;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setInstructor(boolean instructor) {
        isInstructor = instructor;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClassrooms(Set<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}
