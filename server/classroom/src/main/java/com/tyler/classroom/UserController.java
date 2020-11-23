package com.tyler.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/register")
    public @ResponseBody User register(@RequestBody User user) {
        userRepository.save(user);

        return user;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

}
