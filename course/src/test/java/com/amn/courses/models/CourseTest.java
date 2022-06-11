package com.amn.courses.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void getUsers() {
        Course course = new Course();
        User user1 = new User(1, "Profesor1", "prof1", "prof1@etf.unsa.ba", "prof1***3Rm!");
        User user2 = new User(1, "Profesor2", "prof2", "prof2@etf.unsa.ba", "prof2***3Rm!!");
        User user3 = new User(1, "Profesor3", "prof3", "prof3@etf.unsa.ba", "prof3***3Rm!!");

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        course.setUsers(users);

        Assertions.assertEquals(users, course.getUsers());
    }

    @Test
    void setUsers() {
        Course course = new Course();
        User user1 = new User(1, "Saša Mrdović", "smrdovic", "smrdovic@etf.unsa.ba", "sasa123Rm!");
        User user2 = new User(1, "Željko Jurić", "zjuric", "zjuric@etf.unsa.ba", "zjuric123OI!");
        User user3 = new User(1, "Amila Akagić", "aakagic", "aakagic@etf.unsa.ba", "aakagic123Psds!");

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        course.setUsers(users);

        Assertions.assertEquals(3, users.size());
    }

    @Test
    void getId() {
        User professor = new User(1, "Dinko Osmanković", "dosmankovic", "dosmankovic@etf.unsa.ba", "dosmankovicMLTI!");
        Course course = new Course(2456, "Matematička logika", professor);

        Assertions.assertEquals(2456, course.getId());
    }

    @Test
    void setId() {
        Course course = new Course();
        course.setId(789);

        Assertions.assertNotEquals(22456, course.getId());
        Assertions.assertEquals(789, course.getId());
    }

    @Test
    void getName() {
        Course course = new Course();
        course.setName("Praktikum - Napredne Web tehnologije");

        Assertions.assertNotEquals("Negra", course.getName());
        Assertions.assertEquals("Praktikum - Napredne Web tehnologije", course.getName());
    }

    @Test
    void setName() {
        Course course = new Course();
        course.setName("Časovi plivanja");

        Assertions.assertEquals("Časovi plivanja", course.getName());
        Assertions.assertNotEquals("Časovi odbojke", course.getName());
        Assertions.assertNotEquals("Časovi sprota", course.getName());
    }

    @Test
    void getInstructor() {
        Course course = new Course();
        User professor = new User(224, "Negra", "negra", "negra@etf.unsa.ba", "negraaaahm!");

        course.setInstructor(professor);
        Assertions.assertEquals(professor, course.getInstructor());
    }

    @Test
    void setInstructor() {
        Course course = new Course();
        User professor = new User(224, "Adnan", "adnan", "adnan@etf.unsa.ba", "adnananaaaas!");
        User fakeUser = new User(1, "fake", "fake1", "fake@etf.unsa.ba", "fakeIAm!");

        course.setInstructor(professor);
        Assertions.assertEquals(professor, course.getInstructor());
        Assertions.assertNotEquals(fakeUser, course.getInstructor());
    }
}