package sogdice.example.spring.boot.websocket.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@ComponentScan
@EnableAutoConfiguration
@Controller
public class Application {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
