package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCreds;

import java.io.Serializable;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    public final String REG_URL = BASE_URI + "/auth/register";
    public final String LOGIN_URL = BASE_URI + "/auth/login";
    public final String DELETE_URL = BASE_URI +"/auth/user";
    public String accessToken;



    public static User user;

    public UserClient(User user) {

    }
    public UserClient(UserCreds userCreds){

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

    @Step("Login {user}")
    public ValidatableResponse login(UserCreds userCreds) {

        return given()
                .spec(getBaseReqSpec())
                .log().all()
                .body(userCreds)
                .when()
                .post(LOGIN_URL)
                .then().log().all();
    }

    @Step("Login {user}")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization",accessToken)
                .log().all()
                .when()
                .delete(DELETE_URL)
                .then().log().all();
    }


}
