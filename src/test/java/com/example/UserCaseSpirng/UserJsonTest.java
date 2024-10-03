package com.example.UserCaseSpirng;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
public class UserJsonTest {

    @Autowired
    private JacksonTester<Users> json;


    @Test
    void myFirstTest(){
        assertThat(42).isEqualTo(42);
    }
    @Test
    void userSerializationTest() throws IOException {
        Users users = new Users(45L, "Joao", "joao@gmail.com");

        assertThat(json.write(users)).isEqualToJson(new ClassPathResource("expected.json"));
        assertThat(json.write(users)).hasJsonPathValue("@.id");
        assertThat(json.write(users)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(45);
        assertThat(json.write(users)).hasJsonPathStringValue("@.userName");
        assertThat(json.write(users)).extractingJsonPathStringValue("@.userName")
                .isEqualTo("Joao");
        assertThat(json.write(users)).hasJsonPathStringValue("@.email");
        assertThat(json.write(users)).extractingJsonPathStringValue("@.email")
                .isEqualTo("joao@gmail.com");
    }

    @Test
    void userDeserialization() throws IOException  {
        String expected = """
                {
                    "id" : 45,
                    "userName" : "Joao",
                    "email" : "joao@gmail.com"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new Users(45L, "Joao", "joao@gmail.com"));
        assertThat(json.parseObject(expected).id()).isEqualTo(45L);
        assertThat(json.parseObject(expected).userName()).isEqualTo("Joao");
        assertThat(json.parseObject(expected).email()).isEqualTo("joao@gmail.com");
    }
}
