package com.test.simpleuserlist.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class User implements Parcelable {
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

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        photoUrl = in.readString();
        if (in.readByte() == 0) {
            birthDate = null;
        } else {
            birthDate = in.readLong();
        }
        phoneNumbers = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(email);
        parcel.writeString(photoUrl);
        if (birthDate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(birthDate);
        }
        parcel.writeStringList(phoneNumbers);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
