package order;

import client.OrderClient;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import model.Order;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class createOrderWithOutAuthTest {

    Order order = new Order();
    private OrderClient orderClient;

    @Test
    @Description("Создание заказа без авторизации")
    public void createOrderNotAuth() {
        orderClient = new OrderClient(order);
        ValidatableResponse orderResponse =  orderClient.create(order)
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));

    }


}
