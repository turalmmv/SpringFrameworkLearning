package com.example.demo.controller;


import com.example.demo.dto.request.CarRequestDto;
import com.example.demo.dto.respons.CarResponsDto;
import com.example.demo.service.CarService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    public void create(@RequestBody CarRequestDto carDto) {
        carService.create(carDto);
    }


    @GetMapping("/getAll")
    public List<CarResponsDto> getCars() {
        return carService.getCars();
    }


    @GetMapping("/getById")
    public Object getById(@RequestHeader("id") Long id) {
        return carService.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestHeader("id") Long id, @RequestBody CarRequestDto newCar) {
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


    @PostMapping("/sending-email")
    public String sendEmail(@RequestParam String sub, @RequestParam String text) {
        return carService.sendEmail(sub, text);
    }

}