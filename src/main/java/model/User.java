package model;

import client.RestClient;

import static client.RestClient.BASE_URI;

public class User {

    public String email;
    public String password;
    public String name;


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

//    public User(String password, String name) {
//        this.password = password;
//        this.name = name;
//
//    }
//    public User(String password, String email) {
//        this.password = password;
//        this.email = email;
//
//    }
//    public User() {
//
//    }
}
