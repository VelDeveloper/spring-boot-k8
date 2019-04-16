package com.sample.kubernetes.UnitTest;

import com.sample.kubernetes.controller.HelloController;
import com.sample.kubernetes.exception.InvalidIdException;
import com.sample.kubernetes.model.Hello;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
@Ignore
public class HelloControllerTest {

    @Test
    public void TestSayHello() {
        HelloController helloController = new HelloController();
        String result = helloController.sayHello();
        assertEquals(result, "Hello.Welcome to our site!!!!");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloController helloController;

    @Test
    public void testHelloWorld() throws Exception {
        given(helloController.sayHello()).willReturn("Hello.Welcome to our site!!!!");
        mockMvc.perform(get("/sayHello"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello.Welcome to our site!!!!"));
    }

    @Test
    public void testHelloWorldJson() throws Exception {
        Hello hello = Hello.builder()
            .description("Hello how are you.")
            .title("Say hello")
            .build();
        given(helloController.sayHelloPojo()).willReturn(hello);
        mockMvc.perform(get("/sayHello/json/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", Matchers.is("Say hello")))
            .andExpect(jsonPath("$.description", Matchers.is("Hello how are you.")))
            .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

    }

    @Test
    public void testHelloWorldJsonWithId() throws Exception {
        Hello hello = Hello.builder()
            .description("Hello how are you.sample1")
            .title("Say hello")
            .build();
        given(helloController.sayHelloPojoWithId("sample1")).willReturn(hello);
        mockMvc.perform(get("/sayHello/json/"+"sample1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", Matchers.is("Say hello")))
            .andExpect(jsonPath("$.description", Matchers.is("Hello how are you.sample1")))
            .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

    }

    @Test
    public void testHelloWorldJsonWithIdShouldThrowException() throws Exception {
        Hello hello = Hello.builder()
            .description("Hello how are you.sample1")
            .title("Say hello")
            .build();
        this.thrown.expect(InvalidIdException.class);
        this.thrown.expectMessage("Hello ID shouldn't be null.");
        this.helloController.sayHelloPojoWithId("");

    }

    @Test
    public void testHelloWorldJsonList() throws Exception {
        Hello hello = Hello.builder()
            .description("Hello how are you.")
            .title("Say hello")
            .build();
        Hello hello1 = Hello.builder()
            .description("Hello second time.")
            .title("Hello title")
            .build();
        List<Hello> helloList = new ArrayList<>();
        helloList.add(hello);
        helloList.add(hello1);
        given(helloController.sayHelloPojoList("sample1","sample2")).willReturn(helloList);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("param1","sample1");
        params.add("param2", "sample2");
        mockMvc.perform(get("/sayHello/json/list").accept(MediaType.APPLICATION_JSON)
            .params(params))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title", Matchers.is("Say hello")))
            .andExpect(jsonPath("$[0].description", Matchers.is("Hello how are you.sample1")))
            .andExpect(jsonPath("$[1].title", Matchers.is("Hello title")))
            .andExpect(jsonPath("$", Matchers.hasSize(2)));

    }


}
