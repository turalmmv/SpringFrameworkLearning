package com.example.demo.dto.respons;

import com.example.demo.entity.CarEntity;
import lombok.Data;

@Data
public class CustomerResponsDto {
    private Long customerId;
    private String customerName;
    private String customerEmail;
}
