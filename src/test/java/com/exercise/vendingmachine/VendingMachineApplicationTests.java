package com.exercise.vendingmachine;

import com.exercise.vendingmachine.controller.UserController;
import com.exercise.vendingmachine.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(classes = {
        VendingMachineApplication.class})
@ActiveProfiles(value = "test")
class VendingMachineApplicationTests {

    @Test
    void contextLoads() {
    }

    @RunWith(SpringRunner.class)
    @WebMvcTest(value = UserController.class, excludeAutoConfiguration = UserService.class) // or any other configuration.
    public class UserControllerTest {
        private MockMvc mockMvc;
        @MockBean
        private UserService userService;

        @Before
        public void setUp() {
            this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
        }
    }

}
