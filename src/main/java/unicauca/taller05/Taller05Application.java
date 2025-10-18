package unicauca.taller05;

import jakarta.transaction.Transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Transactional
public class Taller05Application {

    public static void main(String[] args) {
        SpringApplication.run(Taller05Application.class, args);
    }


}
