package com.example.demo.controller;


import com.example.demo.dto.CarDto;
import com.example.demo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    public String create(@RequestBody CarDto carDto) {
        return carService.create(carDto);
    }


    @GetMapping("/getAll")
    public LinkedList<CarDto> getCars() {
        return carService.getCars();
    }


    @GetMapping("/getById")
    public Object getById(@RequestHeader("id") Long id) {
        return carService.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestHeader("id") Long id, @RequestBody CarDto newCar) {
        return carService.update(id, newCar);
    }

    @PatchMapping("/update")
    public String updateYearById(@RequestHeader("id") Long id, @RequestParam("newYear") Integer year) {
        return carService.updateYearById(id, year);
    }


    @DeleteMapping("/delete")
    public String delete(@RequestHeader("id") Long id) {
        return carService.delete(id);
    }


}