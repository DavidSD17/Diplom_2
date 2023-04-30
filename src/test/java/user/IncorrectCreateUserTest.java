package user;

import client.UserClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class IncorrectCreateUserTest {


    private User user;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
    }


    @Test
    @Description("Нельзя создать пользователя, который уже зарегистрирован")
    public void createUserDouble() {
        User user = UserGenerator.generateRandomCredentials();

        UserClient userClient = new UserClient();
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200);
        accessToken = loginResponse.extract().path("accessToken");

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
        UserClient userClient = new UserClient();
        ValidatableResponse loginResponse = userClient.create(userNotEmail)

                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
//        accessToken = loginResponse.extract().path("accessToken");

    }

    @Test
    @Description("Нельзя создать пользователя без обязательного поля Password")
    public void createUserWithoutPassword() {
        User user = UserGenerator.generateCredentialsNotPassword();
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
        accessToken = loginResponse.extract().path("accessToken");

    }

    @Test
    @Description("Нельзя создать пользователя без обязательного поля Name")
    public void createUserWithoutName() {
        User user = UserGenerator.generateCredentialsNotName();
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
        accessToken = loginResponse.extract().path("accessToken");

    }

    @After
    @Description("Удаление пользователя")
    public void deleteUser() {
        if(accessToken != null) {
            userClient.delete(accessToken)
                    .assertThat()
                    .statusCode(202);
        }

    }


}
