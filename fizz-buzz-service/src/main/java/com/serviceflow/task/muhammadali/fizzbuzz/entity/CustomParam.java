package com.serviceflow.task.muhammadali.fizzbuzz.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomParam implements Serializable {

    private static final long serialVersionUID = -2523694545220173297L;

    private String orderId;
    private String typeId;
    private List<Rules> rules;
    private String numbers;


}
