package br.com.api.api_teste_mysql.controller;

import br.com.api.api_teste_mysql.model.User;
import br.com.api.api_teste_mysql.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{variable}")
    public String test(@PathVariable(name = "variable") String variable) {
        if (variable.equals("error")) {
            throw new RuntimeException("Error");
        }
        MDC.put("correlationID", variable);
        LoggerFactory.getLogger("teste")
                .atInfo()
                .addKeyValue("variable", variable)
                .setMessage("validando teste")
                .log();
        return "ok";
    }
}
