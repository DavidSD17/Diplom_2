package order;

import client.OrderClient;
import client.UserClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.UserCreds;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersByUser {

    private OrderClient orderClient;
    Order order = new Order();
    private String accessToken;


    @Test
    @Description("Логин под существующим пользователем")
    public void getOrdersWithAuth() {
        UserCreds user = new UserCreds("test33223-data@yandex.ru","password");
        UserClient userClient = new UserClient(user);
        ValidatableResponse loginResponse = userClient.login(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken",notNullValue());
        accessToken = loginResponse.extract().path("accessToken");

        orderClient = new OrderClient(order);
        orderClient.getOrdersByUsers(accessToken)
                .assertThat()
                .statusCode(200)
                .and()
                .body("orders",notNullValue());

    }

    @Test
    @Description("Логин под существующим пользователем")
    public void getOrdersNotAuth() {

        orderClient = new OrderClient(order);
        orderClient.getOrdersNotAuth()
                .assertThat()
                .statusCode(401)
                .and()
                .body("message",equalTo("You should be authorised"));


    }
}
