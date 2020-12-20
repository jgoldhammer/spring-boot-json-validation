package de.jgoldhammer.json.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JsonValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonValidationApplication.class, args);
    }

    @RestController
    private class JsonValidation {

        @PostMapping(path = "/json")
        public void test (@RequestBody JsonExampleModel jsonExampleModel){

		}

    }

}
