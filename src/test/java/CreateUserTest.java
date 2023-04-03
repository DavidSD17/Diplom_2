import client.UserClient;
import io.qameta.allure.Description;
import model.User;
import model.UserGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateUserTest {
    private UserClient userClient;
    User user;


//    @Before
//    public void setUp(){
//        User user = UserGenerator.generateRandomCredentials();
//
//    }

        @Test
        @Description("Курьер может быть создан с корректными данными, подтверждатеся авторизацией и получением id")
    public void createUserSuccess(){
     User user = UserGenerator.generateRandomCredentials();
            UserClient userClient = new UserClient(user);
            userClient.create(user);


    }

}
