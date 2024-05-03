package com.example.demo.controller;

import com.example.demo.dto.request.CustomerRequestDto;
import com.example.demo.dto.respons.CustomerResponsDto;
import com.example.demo.service.CustomerService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Data
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public String create(@RequestBody CustomerRequestDto customerDto) {
        customerService.create(customerDto);
        return "Successfully added";
    }

    @GetMapping("/get-all")
    public List<CustomerResponsDto> getCars() {
        return customerService.getCars();
    }

    @GetMapping("/getById")
    public CustomerResponsDto getById(@RequestHeader("customer_id") Long id) {
        return customerService.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestHeader("customer_id") Long id, @RequestBody CustomerRequestDto newCustomerDto) {
        customerService.update(id, newCustomerDto);
        return "Successfully updated";
    }

    @PatchMapping("/updateYearById")
    public String updateYearById(@RequestHeader("customer_id") Long id, @RequestHeader("customer_email") String email) {
        customerService.updateYearById(id, email);
        return "Email updated";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestHeader("customer_id") Long id) {
        customerService.delete(id);
        return "Successfully deleted";
    }

}
