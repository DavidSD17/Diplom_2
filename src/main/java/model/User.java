package model;

import client.RestClient;

import static client.RestClient.BASE_URI;

public class User {
    public final String REG_URL = BASE_URI+"/auth/register";

    public String email;
    public String password;
    public String name;


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
