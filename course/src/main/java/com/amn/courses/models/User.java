package com.amn.courses.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "no_double_usernames", columnNames = {"username"})
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String username;
    private String email;
    private String password;
    //private List<Role> roles;

    @JsonIgnoreProperties({"users"})
    @ManyToMany(cascade = {
            CascadeType.PERSIST
    }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "enrollment",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id")
            }
    )
    Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private Set<Course> InstructedCourses;

    public User(Integer id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.courses = new HashSet<>();
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getInstructedCourses() {
        return InstructedCourses;
    }

    public void setInstructedCourses(Set<Course> instructedCourses) {
        InstructedCourses = instructedCourses;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
