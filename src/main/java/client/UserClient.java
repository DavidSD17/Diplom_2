package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;

import java.io.Serializable;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    public final String REG_URL = BASE_URI+"/auth/register";


    public static User user;

    public UserClient(User user) {

    }


    @Step("Create {user}")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseReqSpec())
                .log().all()
                .body(user)
                .when()
                .post(REG_URL)
                .then().log().all();

    }
}
