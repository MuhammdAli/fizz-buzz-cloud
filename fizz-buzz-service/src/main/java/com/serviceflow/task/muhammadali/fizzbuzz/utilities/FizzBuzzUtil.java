package com.serviceflow.task.muhammadali.fizzbuzz.utilities;

import com.serviceflow.task.muhammadali.fizzbuzz.entity.CustomParam;
import com.serviceflow.task.muhammadali.fizzbuzz.entity.Rules;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.InvalidRuleIdException;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.MalformedJsonException;
import com.serviceflow.task.muhammadali.fizzbuzz.exceptions.TooManyNumbersException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FizzBuzzUtil {

    //used on reminder
    private static final BigDecimal THREE = new BigDecimal("3");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    //to avoid out of memory the amount of different numbers tested is limited
    private static final Long MAX_NUMBERS = 1000000L;

    /**
     * @param n Passing the StringBuilder that are retrieving the return, it will check n and add the appropriate piece of text to the StringBuilder
     */
    public static String check(Long n) {
        if (n % 3 == 0) {//if it is divisible by 3 add "Fizz"
            String resp = FIZZ;
            if (n % 5 == 0) {//if it is divisible by 3 and 5 add "Fizz Buzz"
                resp += BUZZ;
            }
            return resp;
        } else if (n % 5 == 0) {//if it is divisible by 5 add "Buzz"
            return BUZZ;
        } else {// else add number
            return String.valueOf(n);
        }
    }

    /**
     * @param n Passing the StringBuilder that are retrieving the return, it will check n and add the appropriate piece of text to the StringBuilder
     */
    public static String check(BigDecimal n) {
        if (n.remainder(THREE).equals(BigDecimal.ZERO)) {//if it is divisible by 3 add "Fizz"
            String resp = FIZZ;
            if (n.remainder(FIVE).equals(BigDecimal.ZERO)) {//if it is divisible by 3 and 5 add "Fizz Buzz"
                resp += BUZZ;
            }
            return resp;
        } else if (n.remainder(FIVE).equals(BigDecimal.ZERO)) {//if it is divisible by 5 add "Buzz"
            return BUZZ;
        } else {// else add number
            return String.valueOf(n);
        }
    }

    /**
     * @param cp
     * @return list of numbers
     * As one of the custom parameters is the order, Will be required to transform it into a number list;
     */
    public static List<BigDecimal> retrieveNumbers(CustomParam cp) {
        List<BigDecimal> ret = new ArrayList<>();
        //a Pattern is used to find inside the string only the numbers (1 2 3 4 5), and the 'ranges' (1-59 30-40) any other thing is considerate a separator
        Pattern p = Pattern.compile("(\\d+-\\d+)|\\d+");
        Matcher m = p.matcher(cp.getNumbers());

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
                        ret.add(i);
                        n++;
                        if (n > MAX_NUMBERS) { //number of tests is grater than the limit
                            throw new TooManyNumbersException();
                        }
                    }
                }
            } else {
                //number
                ret.add(new BigDecimal(number));
                n++;
                if (n > MAX_NUMBERS) {//number of tests is grater than the limit
                    throw new TooManyNumbersException();
                }
            }
        }
        return ret;
    }

    /**
     * @param n Passing the StringBuilder that are retrieving the return, it will check n and add the appropriate piece of text to the StringBuilder
     */
    public static String check(BigDecimal n, CustomParam cp) {
        StringBuilder ret = new StringBuilder();
        for (Rules r : cp.getRules()) {
            if (r.getId().compareTo(BigDecimal.ONE) < 0) {
                throw new InvalidRuleIdException();
            }
            if (cp.getTypeId().equals("D")) {//Divisible
                if (n.remainder(r.getId()).equals(BigDecimal.ZERO)) {
                    ret.append(r.getDesc());
                }
            } else if (cp.getTypeId().equals("C")) {//Containing
                if (n.toString().contains(r.getId().toString())) {
                    ret.append(r.getDesc());
                }
            } else if (cp.getTypeId().equals("B")) {//Both
                if (n.remainder(r.getId()).equals(BigDecimal.ZERO) || n.toString().contains(r.getId().toString())) {
                    ret.append(r.getDesc());
                }
            } else {//exception if it's not an expected id
                throw new MalformedJsonException();
            }

        }

        if (ret.length() == 0) {
            ret.append(n);
        }
        return ret.toString();

    }


}
