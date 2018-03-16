package com.serviceflow.task.muhammadali.fizzbuzz.controller;

import com.serviceflow.task.muhammadali.fizzbuzz.constant.GenericConstants;
import com.serviceflow.task.muhammadali.fizzbuzz.entity.Response;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.TooManyNumbersException;
import com.serviceflow.task.muhammadali.fizzbuzz.utilities.FizzBuzzUtil;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


/**
 * @author Muhammad Ali
 * <p>
 * ClassicController is the controller responsible to perform the main task of the REST API
 * It provide 2 main methods, the first is based on performance so it uses Long Class to manipulate the numbers
 * the second one is based on large numbers, which makes possiblibility for any number tried, So it uses BigDecimal instead of Long
 */

@RestController
public class ClassicController extends FizzBuzzController {

    //to avoid out of memory the amount of different numbers tested is limited
    private static final Long MAX_NUMBERS = 1000000L;

    /**
     * @param numbers
     * @return Json with the system result
     * This is the basic implementation to solve FizzBuzz game, this implementation is based on Long to provide a faster result
     */
    @RequestMapping(value = "/classicFast/{numbers}", method = RequestMethod.GET)
    public Response solveClassic(@PathVariable String numbers) {

        //a Pattern is used to find inside the string only the numbers (1 2 3 4 5), and the 'ranges' (1-59 30-40) any other thing is considerate a separator
        Pattern p = Pattern.compile("(\\d+-\\d+)|\\d+");
        Matcher m = p.matcher(numbers);
        //the output will be stored on a StringJoiner
        StringJoiner joiner = new StringJoiner(",");

        //n will count how many numbers has been tested
        AtomicInteger n = new AtomicInteger();

        while (m.find()) {
            String number = m.group();
            //test if the first pattern is a number or a range
            if (number.contains("-")) {//range
                String[] range = number.split("-");
                joiner.add(
                        LongStream
                                .rangeClosed(Long.parseLong(range[0]), Long.parseLong(range[1]))
                                .limit(MAX_NUMBERS)
                                .mapToObj(lo -> {
                                    n.getAndIncrement();
                                    if (n.get() > MAX_NUMBERS) { //number of tests is grater than the limit
                                        throw new TooManyNumbersException();
                                    }
                                    return FizzBuzzUtil.check(lo);
                                })
                                .map(Object::toString)
                                .collect(Collectors.joining(","))
                );

            } else {//number
                joiner.add(
                        FizzBuzzUtil.check(Long.valueOf(number))
                );//perform check
                n.getAndIncrement();
                if (n.get() > MAX_NUMBERS) {//number of tests is grater than the limit
                    throw new TooManyNumbersException();
                }
            }
        }

        //return the Json
        return new Response(GenericConstants.RESULT.getValue(), joiner.toString());

    }

    /**
     * @param numbers
     * @return Json with the system result
     * This implementation is based on BigDecimal to provide a greater range of results
     */
    @RequestMapping(value = "/classicBigNumber/{numbers}", method = RequestMethod.GET)
    public Response solveClassicBigNumber(@PathVariable String numbers) {

        //a Pattern is used to find inside the string only the numbers (1 2 3 4 5), and the 'ranges' (1-59 30-40) any other thing is considerate a separator
        Pattern p = Pattern.compile("(\\d+-\\d+)|\\d+");
        Matcher m = p.matcher(numbers);
        //the output will be stored on a StringJoiner
        StringJoiner joiner = new StringJoiner(",");

        //n will count how many numbers has been tested
        int n = 0;

        while (m.find()) {
            String number = m.group();
            //test if the first pattern is a number or a range
            if (number.contains("-")) {//range
                String[] range = number.split("-");
                BigDecimal min = new BigDecimal(range[0]);
                BigDecimal max = new BigDecimal(range[1]);
                if (min.compareTo(max) <= 0) {//if the first number is grater than the last (9-4) it will be ignored
                    for (BigDecimal i = min; i.compareTo(max) <= 0; i = i.add(BigDecimal.ONE)) {//for each number in range it will perform the check
                        joiner.add(FizzBuzzUtil.check(i));
                        n++;
                        if (n > MAX_NUMBERS) { //number of tests is grater than the limit
                            throw new TooManyNumbersException();
                        }
                    }
                }
            } else {
                joiner.add(
                        FizzBuzzUtil.check(new BigDecimal(number))
                );//perform check
                n++;
                if (n > MAX_NUMBERS) {//number of tests is grater than the limit
                    throw new TooManyNumbersException();
                }
            }
        }

        //return the Json
        return new Response(GenericConstants.RESULT.getValue(), joiner.toString());
    }

}
