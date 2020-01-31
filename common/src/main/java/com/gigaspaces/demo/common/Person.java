package com.gigaspaces.demo.common;


import com.gigaspaces.annotation.pojo.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Person")
@SpaceClass
public class Person implements Serializable {

    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;

    public Person() {
    }

    @Id
    @SpaceId
    @SpaceIndex(unique = true)
    @Column(name = "Id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "Age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(getLastName(), person.getLastName()) &&
                Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getAge(), person.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getLastName(), getFirstName(), getAge());
    }
}