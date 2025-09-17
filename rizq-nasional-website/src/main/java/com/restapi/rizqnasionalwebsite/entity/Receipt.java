package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private String id;
    private String pakejId;
    private String userId;
    private byte[] uploadedFile;
    private String uploadedDate;
}
