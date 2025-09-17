package com.restapi.rizqnasionalwebsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptResponse {
    private String id;
    private String namaPakej;
    private String fullName;
    private byte[] uploadedFile;
    private String uploadedDate;
}
