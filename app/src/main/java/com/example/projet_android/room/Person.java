package com.example.projet_android.room;


import androidx.room.Entity;

@Entity(tableName = "persons")
public class Person extends User {
    private int age;

    public Person(String name, String phone, String email, String photo, String address, String password, int age) {
        super(name, phone, email, photo, address, password);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
