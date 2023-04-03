package model;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User generateRandomCredentials(){
        String email = RandomStringUtils.randomAlphabetic(7);
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = RandomStringUtils.randomAlphabetic(9);
        return new User(email,password,name);
    }
}
