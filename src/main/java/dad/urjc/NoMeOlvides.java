package dad.urjc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Config.class)
public class NoMeOlvides {

    public static void main(String[] args) {
        SpringApplication.run(NoMeOlvides.class, args);
    }
}
