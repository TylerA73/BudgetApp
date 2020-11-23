package com.tyler.classroom;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "classroom_name")
    @JsonProperty("name")
    private String name;

    @Column(name = "classroom_description")
    @JsonProperty("description")
    private String description;

    @Column(name = "classroom_code")
    @JsonProperty("code")
    private String code;

    @ManyToMany(mappedBy = "classrooms")
    //@JsonProperty("students")
    private Set<User> students;

    /*
    @OneToOne(mappedBy = "owner_id", cascade = CascadeType.ALL)
    private User owner;
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /*
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

     */
}
