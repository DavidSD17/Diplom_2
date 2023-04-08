package user;

import client.UserClient;
import io.qameta.allure.Description;
import model.User;
import model.UserGenerator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class IncorrectCreateUserTest {


    private User user;
    private UserClient userClient;


    @Test
    @Description("Ytkmpz создать пользователя, который уже зарегистрирован")
    public void createUserDouble() {
        User user = new User("test33223-data@yandex.ru","password","Username112");
        UserClient userClient = new UserClient(user);
        userClient.create(user)
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("User already exists"));

    }

    @Test
    @Description("Нельзя создать пользователя без обязательного поля Email")
    public void createUserWithoutEmail() {
        User userNotEmail = UserGenerator.generateCredentialsNotEmail();
        UserClient userClient = new UserClient(userNotEmail);
        userClient.create(userNotEmail)
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));

    }

    @Test
    @Description("Нельзя создать пользователя без обязательного поля Password")
    public void createUserWithoutPassword() {
        User user = UserGenerator.generateCredentialsNotPassword();
        UserClient userClient = new UserClient(user);
        userClient.create(user)
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @Description("Нельзя создать пользователя без обязательного поля Name")
    public void createUserWithoutName() {
        User user = UserGenerator.generateCredentialsNotName();
        UserClient userClient = new UserClient(user);
        userClient.create(user)
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));

    }



}
