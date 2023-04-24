package user;

import client.UserClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCreds;
import model.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginUserTest {

    private User user;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient(user);
    }

    @Test
    @Description("Логин под существующим пользователем")
    public void loginUserSuccess() {
        User user = UserGenerator.generateRandomCredentials();
        UserClient userClient = new UserClient(user);
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
       String email = loginResponse.extract().path("user.email");
       String password = user.getPassword();
        UserCreds userCreds = new UserCreds(email,password);
        userClient.login(userCreds)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken",notNullValue());
         accessToken = loginResponse.extract().path("accessToken");


    }
    @Test
    @Description("Логин с неверным логином и паролем")
    public void loginUserIncorrectCreds() {
        UserCreds user = new UserCreds("test88888-data@yandex1.ru","password");
        UserClient userClient = new UserClient(user);
        userClient.login(user)
                .assertThat()
                .statusCode(401)
                .and()
                .body("message",equalTo("email or password are incorrect"));

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
