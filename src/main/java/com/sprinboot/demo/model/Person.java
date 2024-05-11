package com.sprinboot.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Date;
import java.util.UUID;


public class Person {

    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    private Date dob;
    private String location;


    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name, @JsonProperty("email") String email, Date dob, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.location = location;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getDob() {
        return dob;
    }

    public String getLocation() { return location; }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) { this.email = email; }

    public void setDob(Date dob) { this.dob = dob; }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", location=" + location +
                '}';
    }
}
