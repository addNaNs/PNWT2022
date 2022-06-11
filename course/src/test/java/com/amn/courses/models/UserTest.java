package com.amn.courses.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void instructedCourses() {
        User instructor = new User(100,"Irfan","iprazina1","iprazina1@etf.unsa.ba","sifra123");
        Course course1 = new Course(100,"Web Tehnologije",instructor);
        Course course2 = new Course(101,"Napredne Web Tehnologije", instructor);
        Course course3 = new Course(102,"Razvoj Mobilnih Aplikacija", instructor);

        Set<Course> courses = new HashSet<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        instructor.setInstructedCourses(courses);
        Assertions.assertEquals(courses, instructor.getInstructedCourses());
    }

    @Test
    void courses() {
        User instructor = new User(100,"Irfan","iprazina1","iprazina1@etf.unsa.ba","sifra123");
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        Course course1 = new Course(100,"Web Tehnologije",instructor);
        Course course2 = new Course(101,"Napredne Web Tehnologije", instructor);
        Course course3 = new Course(102,"Razvoj Mobilnih Aplikacija", instructor);

        Set<Course> courses = new HashSet<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        user.setCourses(courses);
        Assertions.assertEquals(courses, user.getCourses());
    }

    @Test
    void getId() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        Assertions.assertEquals(18459,user.getId());
    }

    @Test
    void setId() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        user.setId(18469);
        Assertions.assertNotEquals(18459,user.getId());
        Assertions.assertEquals(18469,user.getId());
    }

    @Test
    void getName() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        Assertions.assertEquals("Muhamed", user.getName());
    }

    @Test
    void setName() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        user.setName("Muhamed Opačin");
        Assertions.assertNotEquals("Muhamed",user.getName());
        Assertions.assertEquals("Muhamed Opačin",user.getName());
    }

    @Test
    void getEmail() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        Assertions.assertEquals("mopacin1@etf.unsa.ba", user.getEmail());
    }

    @Test
    void setEmail() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        user.setEmail("mopacin2@etf.unsa.ba");
        Assertions.assertNotEquals("mopacin1@etf.unsa.ba",user.getEmail());
        Assertions.assertEquals("mopacin2@etf.unsa.ba",user.getEmail());
    }

    @Test
    void getUsername() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        Assertions.assertEquals("mopacin1", user.getUsername());
    }

    @Test
    void setUsername() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        user.setUsername("muhamedo");
        Assertions.assertNotEquals("mopacin1",user.getUsername());
        Assertions.assertEquals("muhamedo",user.getUsername());
    }

    @Test
    void getPassword() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        Assertions.assertEquals("sifra123", user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User(18459, "Muhamed", "mopacin1", "mopacin1@etf.unsa.ba", "sifra123");
        user.setPassword("123sifra");
        Assertions.assertNotEquals("sifra123",user.getPassword());
        Assertions.assertEquals("123sifra",user.getPassword());
    }
}