package com.example.demo.dto.request;

import lombok.Data;

@Data
public class CarRequestDto {
    private String modelName;
    private Integer carYear;
    private Long customerId;

}
