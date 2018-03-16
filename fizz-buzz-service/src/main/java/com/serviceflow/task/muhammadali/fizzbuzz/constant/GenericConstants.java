package com.serviceflow.task.muhammadali.fizzbuzz.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenericConstants {
    RESULT("result"),
    ERROR("error"),
    DESCRIPTION("desc");
    private final String value;
}
