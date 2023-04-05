import client.UserClient;
import io.qameta.allure.Description;
import model.User;
import model.UserCreds;
import model.UserGenerator;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginUserTest {

    @Test
    @Description("Логин под существующим пользователем")
    public void loginUserSuccess() {
        UserCreds user = new UserCreds("test33223-data@yandex.ru","password");
        UserClient userClient = new UserClient(user);
        userClient.login(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken",notNullValue());

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
}
