package com.tw.SpringBootSkeletonClient;


import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "http://localhost:8080",
        classes = UserServiceClient.class)
public class UserServiceContractTest {

    @Autowired
    private UserServiceClient userServiceClient;

    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("Spring-boot-skeleton", null,
            5000, this);

    @Pact(consumer = "SpringBootSkeletonClient")
    public RequestResponsePact pactUserExists(PactDslWithProvider builder) {
        return builder.given("foo")
                .uponReceiving("passed")
                .path("/user/2")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(LambdaDsl.newJsonBody((o) -> o
                        .stringType("id", "2")
                        .stringType("firstname", "foo")
                        .stringType("lastname", "soe")
                ).build())
                .toPact();
    }

    @PactVerification(fragment = "pactUserExists")
    @Test
    public void userExists() {
        User user = userServiceClient.getUser("2");
        System.out.println("User " + user.getFirstname());
        assertThat(user.getFirstname()).isEqualTo("foo");
    }

}