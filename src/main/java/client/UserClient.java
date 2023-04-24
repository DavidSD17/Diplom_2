package client;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;
import model.UserCreds;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {

    private final String REG_URL =  "https://stellarburgers.nomoreparties.site/api/auth/register";
    private final String LOGIN_URL =  "https://stellarburgers.nomoreparties.site/api/auth/login";
    private final String USER_URL = "https://stellarburgers.nomoreparties.site/api/auth/user";


    public static User user;

    public UserClient(User user) {
        this.user = user;
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

    @Step("Delete {user}")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization",accessToken)
                .log().all()
                .when()
                .delete(USER_URL)
                .then().log().all();
    }

    @Step("change data with auth {user}")
    public ValidatableResponse changeDataWithAuth(String accessToken, String userData) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization",accessToken)
                .body(userData)
                .log().all()
                .when()
                .patch(USER_URL)
                .then().log().all();
    }

    @Step("change data not auth {user}")
    public ValidatableResponse changeDataNotAuth(String userName) {
        return given()
                .spec(getBaseReqSpec())
                .body(userName)
                .log().all()
                .when()
                .patch(USER_URL)
                .then().log().all();
    }


}
