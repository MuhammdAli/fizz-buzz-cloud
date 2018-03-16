package com.serviceflow.task.muhammadali.fizzbuzz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.serviceflow.task.muhammadali.fizzbuzz.FizzBuzzApplication;
import com.serviceflow.task.muhammadali.fizzbuzz.constant.GenericConstants;
import com.serviceflow.task.muhammadali.fizzbuzz.entity.CustomParam;
import com.serviceflow.task.muhammadali.fizzbuzz.entity.Rules;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FizzBuzzApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CustomControllerTest {

    @Value("${local.server.port}")
    int port;

    @Autowired
    ObjectMapper mapper;

    @Before
    public void setUp() {

        RestAssured.port = port;
        RestAssured.registerParser("text/plain", Parser.JSON);
    }

    //Test of custom Return
    @Test
    public void customReturnOk() {
        String ret = "1,2,Fizz,4,Buzz";

        CustomParam cp = new CustomParam();
        cp.setNumbers("1 2 3 4 5");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customReturnOkAscending() {
        String ret = "1,2,Fizz,4,Buzz";

        CustomParam cp = new CustomParam();
        cp.setNumbers("5 4 3 2 1");
        cp.setOrderId("A");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customReturnOkDescending() {
        String ret = "Buzz,4,Fizz,2,1";

        CustomParam cp = new CustomParam();
        cp.setNumbers("1 2 3 4 5");
        cp.setOrderId("D");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customReturnOkContaining() {
        String ret = "1,2,Fizz,4,Buzz,Fizz,FizzBuzz";

        CustomParam cp = new CustomParam();
        cp.setNumbers("1 2 3 4 5 31 53");
        cp.setOrderId("U");
        cp.setTypeId("C");
        cp.setRules(new ArrayList<>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customReturnOkBoth() {
        String ret = "1,2,Fizz,4,Buzz,Fizz,FizzBuzz,FizzBuzz";

        CustomParam cp = new CustomParam();
        cp.setNumbers("1 2 3 4 5 31 53 30");
        cp.setOrderId("U");
        cp.setTypeId("B");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customReturnOkRules() {
        String ret = "1,2,Fizz,4,Buzz,Fizz,Woof";

        CustomParam cp = new CustomParam();
        cp.setNumbers("1 2 3 4 5 6 7");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));
        cp.getRules().add(new Rules(new BigDecimal("7"), "Woof"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customReturnOkWithChars() {
        String ret = "1,2,Fizz,4,Buzz";

        CustomParam cp = new CustomParam();
        cp.setNumbers("1 2ssdfxckwlfthi!@#$%¨&*()_+qwertyuiop´[asdfghjklç~]zxcvbnm,;3 4 5");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    @Test
    public void customReturnOkLimitNumbers() {
        //didn't test the body because I don't have the output
        CustomParam cp = new CustomParam();
        cp.setNumbers("1-1000000");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value());

    }

    @Test
    public void customReturnOkBigNumbers() {
        String ret = "1,Buzz";


        CustomParam cp = new CustomParam();
        cp.setNumbers("1 20000000000000000000000000000000000000000");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.OK.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customFailLimitNumbers() {
        String ret = "Too bad, you choose too many numbers, and this overload our servers, please try again with fewer numbers";


        CustomParam cp = new CustomParam();
        cp.setNumbers("1-1000001");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.INSUFFICIENT_STORAGE.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customFailInvalidOrder() {
        String ret = "The Json parameter is invalid!";


        CustomParam cp = new CustomParam();
        cp.setNumbers("1-1000");
        cp.setOrderId("X");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.EXPECTATION_FAILED.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customFailInvalidType() {
        String ret = "The Json parameter is invalid!";


        CustomParam cp = new CustomParam();
        cp.setNumbers("1-1000");
        cp.setOrderId("U");
        cp.setTypeId("X");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.EXPECTATION_FAILED.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customFailInvalidRules() {
        String ret = "The Json parameter is invalid!";


        CustomParam cp = new CustomParam();
        cp.setNumbers("1-1000");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("5"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        param = param.replace("5", "X");
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

    @Test
    public void customFailInvalidRulesZero() {
        String ret = "The Rules Id must be greater than 0!";


        CustomParam cp = new CustomParam();
        cp.setNumbers("1-1000");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("0"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.EXPECTATION_FAILED.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }

    @Test
    public void customFailInvalidRulesNegative() {
        String ret = "The Rules Id must be greater than 0!";


        CustomParam cp = new CustomParam();
        cp.setNumbers("1-1000");
        cp.setOrderId("U");
        cp.setTypeId("D");
        cp.setRules(new ArrayList<Rules>());
        cp.getRules().add(new Rules(new BigDecimal("3"), "Fizz"));
        cp.getRules().add(new Rules(new BigDecimal("-1"), "Buzz"));

        String param = "";
        try {
            param = mapper.writeValueAsString(cp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        given()
                .contentType("application/json")  //another way to specify content type
                .body(param)
                .when().post("/custom")
                .then().statusCode(HttpStatus.EXPECTATION_FAILED.value())
                .body(GenericConstants.DESCRIPTION.getValue(), Matchers.is(ret));

    }


    @Test
    public void customFailInvalidJson() {
        String ret = "The Json parameter is invalid!";
        given()
                .contentType("application/json")  //another way to specify content type
                .body("{sadsd}")
                .when().post("/custom")
                .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }


}
