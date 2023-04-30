package order;

import client.OrderClient;
import client.UserClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.User;
import model.UserCreds;
import model.UserGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersByUser {

    private OrderClient orderClient;
    Order order = new Order();
    private String accessToken;


    @Test
    @Description("Получить заказы под авторизованым пользователем")
    public void getOrdersWithAuth() {
        User user = UserGenerator.generateRandomCredentials();
        UserClient userClient = new UserClient();

        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");

        orderClient = new OrderClient();
        orderClient.getOrdersByUsers(accessToken)
                .assertThat()
                .statusCode(200)
                .and()
                .body("orders",notNullValue());

    }

    @Test
    @Description("Нельзя получить заказы без авторизации")
    public void getOrdersNotAuth() {

        orderClient = new OrderClient();
        orderClient.getOrdersNotAuth()
                .assertThat()
                .statusCode(401)
                .and()
                .body("message",equalTo("You should be authorised"));


    }
}
