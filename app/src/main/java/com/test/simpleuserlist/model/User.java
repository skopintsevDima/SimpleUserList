package com.test.simpleuserlist.model;

import java.util.List;

public class User {
    public String firstName;
    public String lastName;
    public String email;
    public String photoUrl;
    public Long birthDate;
    public List<String> phoneNumbers;
    public Gender gender;

    public User(String firstName,
                String lastName,
                String email,
                String photoUrl,
                Long birthDate,
                List<String> phoneNumbers,
                Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.birthDate = birthDate;
        this.phoneNumbers = phoneNumbers;
        this.gender = gender;
    }
}
