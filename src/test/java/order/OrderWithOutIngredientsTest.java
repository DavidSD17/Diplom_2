package order;
import client.OrderClient;

import client.UserClient;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import model.Order;
import model.UserGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class OrderWithOutIngredientsTest {

    private OrderClient  orderClient;
    private String[] ingredients = {};
    private String[] incorrectIngredients = {"FAKEc5a71d1f82001bdaaa70","61c0c5a71d1f82001bdaaFAKE"};


    Order order = new Order(ingredients);
    Order fakeHash = new Order(incorrectIngredients);
    Gson gson = new Gson();
    String json = gson.toJson(order);
    String fakeHashJson = gson.toJson(fakeHash);

    @Before
    public void setUp() {

        orderClient = new OrderClient(order);
    }

    @Test
    @Description("Создание заказа без ингредиентов")
    public void createOrderNotIngredients() {

        orderClient.createWithoutIngredients(json)
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));

    }

    @Test
    @Description("Создание заказа с некорректным хэш ингредиента")
    public void createOrderWithFakeHash() {

        orderClient.createWithoutIngredients(fakeHashJson)
                .assertThat()
                .statusCode(500);

    }
}
