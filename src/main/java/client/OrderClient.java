package client;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Order;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    public final String ORDER_URL = BASE_URI+"/orders";

    public static Order order;

    public OrderClient(Order order){

    }


    @Step("Create {order}")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getBaseReqSpec())
                .log().all()
                .body(order)
                .when()
                .post(ORDER_URL)
                .then().log().all();
    }

    @Step("Create {order}")
    public ValidatableResponse createWithoutIngredients(String order) {
        return given()
                .spec(getBaseReqSpec())
                .log().all()
                .body(order)
                .when()
                .post(ORDER_URL)
                .then().log().all();
    }

    @Step("Login {user}")
    public ValidatableResponse delete(String orderId) {
        return given()
                .spec(getBaseReqSpec())
                .header("Authorization",orderId)
                .log().all()
                .when()
                .delete()
                .then().log().all();
    }
}
