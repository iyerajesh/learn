package com.xylia.java.learn.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
public class Employee {

    @Getter
    private int employeeId;
    @Getter
    private String name;
    @Getter
    private String department;
}
