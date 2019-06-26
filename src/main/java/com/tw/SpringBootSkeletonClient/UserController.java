package com.tw.SpringBootSkeletonClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserServiceClient userServiceClient;

    @Autowired
    public UserController(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @GetMapping(value = "/foo/{id}")
    public String foo(@PathVariable String id) {
        User user = userServiceClient.getUser(id);
        System.out.println("user = " + user);
        return "foo";
    }
}
