package com.serviceflow.task.muhammadali.fizzbuzz.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rules implements Serializable {

    private static final long serialVersionUID = 1559641595489252202L;

    private BigDecimal id;
    private String desc;


}
