import client.UserClient;
import io.qameta.allure.Description;
import model.User;
import model.UserGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateUserTest {
    private UserClient userClient;
//    private User user;


    @Test
    @Description("При регистрации создаётся новый пользователь")
    public void createUserSuccess() {
        User user = UserGenerator.generateRandomCredentials();
        UserClient userClient = new UserClient(user);
        userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken",notNullValue());


    }

    @Test
    @Description("Ytkmpz создать пользователя, который уже зарегистрирован")
    public void createUserDouble() {
        User user = UserGenerator.generateRandomCredentials();
        UserClient userClient = new UserClient(user);
        userClient.create(user)
                .assertThat()
                .statusCode(200);
        userClient.create(user)
                .assertThat()
                .statusCode(403)
                .and()
                .body("message",equalTo("User already exists"));

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
