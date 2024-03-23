package co.istad.springwebmvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;

@SpringBootTest
class SpringWebMvcApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void main() {
        String[] args = { "abc", "abc", "abc" };
        SpringWebMvcApplication.main(args);
    }

}
