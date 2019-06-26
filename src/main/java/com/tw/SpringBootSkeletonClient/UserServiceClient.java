package com.tw.SpringBootSkeletonClient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    private final RestTemplate restTemplate;

    public UserServiceClient(@Value("http://localhost:5000") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public User getUser(String id) {
        return restTemplate.getForObject("/user/" + id, User.class);
    }

}

class User {
    public User(){}

    public Integer getId() {
        return id;
    }

    public User(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    private Integer id;

    private String firstname;

    private String lastname;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}