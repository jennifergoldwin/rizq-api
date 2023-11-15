package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    private String id;
    private String planType;
    private double interest;
    private int tenure;
    private int price;
}
