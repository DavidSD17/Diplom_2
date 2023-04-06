import client.UserClient;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class ChangeDataUserTest {
    private User user;
    private UserClient userClient;
    private String accessToken;

    Gson gson = new Gson();
    String userName = gson.toJson(new User("NewName"));
    String userEmail = gson.toJson(new User("new@email.da",null));
    String userPass = gson.toJson(new User(null,"newPass123098"));




    @Before
    public void setUp() {
        user = UserGenerator.generateRandomCredentials();
        userClient = new UserClient(user);
    }

    @Test
    @Description("Можно изменить поле name")
    public void changeNameUserWithAuth() {
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");
        userClient.changeDataWithAuth(accessToken, userName)
                .assertThat()
                .statusCode(200)
                .and()
                .body("user.name",containsString("NewName"));

    }

    @Test
    @Description("Можно изменить поле email")
    public void changeEmailUserWithAuth() {
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");
        userClient.changeDataWithAuth(accessToken, userEmail)
                .assertThat()
                .statusCode(200)
                .and()
                .body("user.email",containsString("new@email.da"));

    }

    @Test
    @Description("Можно изменить поле password")
    public void changePassUserWithAuth() {
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");
        userClient.changeDataWithAuth(accessToken, userPass)
                .assertThat()
                .statusCode(200);

    }

    @Test
    @Description("Нельзя изменить поле Name без авторизации")
    public void changeNameUserNotAuth() {
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");
        userClient.changeDataNotAuth(userName)
                .assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("You should be authorised"));

    }

    @Test
    @Description("Нельзя изменить поле Email без авторизации")
    public void changeEmailUserNotAuth() {
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");
        userClient.changeDataNotAuth(userEmail)
                .assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("You should be authorised"));

    }

    @Test
    @Description("Нельзя изменить поле Password без авторизации")
    public void changePassUserNotAuth() {
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");
        userClient.changeDataNotAuth(userPass)
                .assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("You should be authorised"));

    }

    @After
    @Description("Нельзя создать пользователя без обязательного поля Name")
    public void deleteUser() {
        userClient.delete(accessToken)
                .assertThat()
                .statusCode(202);

    }
}
