package model;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User generateRandomCredentials(){
        String email = RandomStringUtils.randomAlphabetic(7)+"@davids.crew";
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = RandomStringUtils.randomAlphabetic(9);
        return new User(email,password,name);
    }

    public static User generateCredentialsNotEmail(){
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = RandomStringUtils.randomAlphabetic(9);
        return new User(password,name);
    }
    public static User generateCredentialsNotPassword(){
        String email = RandomStringUtils.randomAlphabetic(7)+"@davids.crew";
        String password = "";
        String name = RandomStringUtils.randomAlphabetic(9);
        return new User(email,password,name);
    }

    public static User generateCredentialsNotName(){
        String email = RandomStringUtils.randomAlphabetic(7)+"@davids.crew";
        String password = RandomStringUtils.randomAlphabetic(8);
        String name = "";
        return new User(email,password,name);
    }
}
