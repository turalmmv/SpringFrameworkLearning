package com.example.demo.dto.respons;

import com.example.demo.entity.CustomerEntity;
import lombok.Data;

@Data
public class CarResponsDto {
    private Long carId;
    private String modelName;
    private Integer carYear;
    private CustomerEntity customerEntity;
}
