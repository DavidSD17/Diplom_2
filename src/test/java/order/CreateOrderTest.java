package order;

import client.OrderClient;
import client.UserClient;
import com.google.gson.Gson;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.User;
import model.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateOrderTest{
    private OrderClient orderClient;
    Order order = new Order();


    private UserClient userClient;
    private User user;
    private String accessToken;
    private int orderId;


    public CreateOrderTest() {
    }


    @Before
    public void setUp() {
        user = UserGenerator.generateRandomCredentials();
        userClient = new UserClient(user);
        orderClient = new OrderClient(order);
    }

    @Test
    @Description("Создание заказа с авторизацией")
    public void createOrderSuccess() {
        ValidatableResponse loginResponse = userClient.create(user)
                .assertThat()
                .statusCode(200)
                .and()
                .body("accessToken", notNullValue());
        accessToken = loginResponse.extract().path("accessToken");

        ValidatableResponse orderResponse =  orderClient.create(order)
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
        orderId = orderResponse.extract().path("order.number");

    }



        @After
        @Description("Удаление пользователя")
        public void deleteUser(){
            userClient.delete(accessToken)
                    .assertThat()
                    .statusCode(202);
        }
    }


