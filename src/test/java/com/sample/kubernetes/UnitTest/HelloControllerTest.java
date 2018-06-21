package com.sample.kubernetes.UnitTest;

import com.sample.kubernetes.controller.HelloController;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloControllerTest {

    @Test
    public void TestSayHello() {
        HelloController helloController = new HelloController();
        String result = helloController.sayHello();
        assertEquals(result, "Hello.Welcome to our site!!!!");
    }
}
