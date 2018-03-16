package com.serviceflow.task.muhammadali.fizzbuzz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {

    private static final long serialVersionUID = 1559641595489252202L;

    private String msg;
    private String desc;


}
