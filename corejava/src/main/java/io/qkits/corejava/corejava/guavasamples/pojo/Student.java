package io.qkits.corejava.corejava.guavasamples.pojo;

import com.google.common.base.MoreObjects;

import java.util.Date;

public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String address;
    private Float grades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getGrades() {
        return grades;
    }

    public void setGrades(Float grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("firstName",firstName)
                .add("lastName",lastName)
                .add("grades",grades)
                .add("address",address)
                .add("birtOfDate",birthday)
                .add("Id",id).toString();
    }
}