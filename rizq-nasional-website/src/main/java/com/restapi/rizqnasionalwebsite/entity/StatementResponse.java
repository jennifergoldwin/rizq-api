package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StatementResponse extends Statement{
    private String userName;
   
}
