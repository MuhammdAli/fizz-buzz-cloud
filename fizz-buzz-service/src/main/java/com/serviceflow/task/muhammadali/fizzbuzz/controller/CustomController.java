package com.serviceflow.task.muhammadali.fizzbuzz.controller;

import com.serviceflow.task.muhammadali.fizzbuzz.constant.GenericConstants;
import com.serviceflow.task.muhammadali.fizzbuzz.entity.CustomParam;
import com.serviceflow.task.muhammadali.fizzbuzz.entity.Response;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.MalformedJsonException;
import com.serviceflow.task.muhammadali.fizzbuzz.utilities.FizzBuzzUtil;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Muhammad Ali
 * CustomController is the controller responsible to perform the additional task of the REST API
 * It provide one method, that is to execute a custom game with the rules choosen by player
 */
@RestController
public class CustomController extends FizzBuzzController {


    /**
     * @param param
     * @return Json with the system result
     * This is the basic class to solve FizzBuzz game, this implementation is based on Long to provide a faster result
     */
    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public Response solveCustom(@RequestBody CustomParam param) {

        //list of numbers that will be tested
        List<BigDecimal> numbersList = FizzBuzzUtil.retrieveNumbers(param);
        if (param.getOrderId().equals("A")) {//Ascending order
            Collections.sort(numbersList);
        } else if (param.getOrderId().equals("D")) {
            Collections.sort(numbersList, Collections.reverseOrder());
        } else if (!param.getOrderId().equals("U")) {//exception if it's not an expected id
            throw new MalformedJsonException();
        }

        //the output will be stored
        String response = numbersList.stream().map(bd -> FizzBuzzUtil.check(bd, param))
                .collect(Collectors.joining(","));

        //return the Json
        return new Response(GenericConstants.RESULT.getValue(), response);
    }


}
