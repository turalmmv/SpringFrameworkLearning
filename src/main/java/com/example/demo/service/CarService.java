package com.example.demo.service;

import com.example.demo.dto.CarDto;
import com.example.demo.entity.CarEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CarService {
    private final ModelMapper modelMapper;
    private final LinkedList<CarDto> carDtos = new LinkedList<>();

    public String create(CarDto carDto) {
        carDtos.add(carDto);
        return "Successfully added";
    }

    public LinkedList<CarDto> getCars() {
        return carDtos;
    }

    public Object getById(Long id) {
        for (CarDto carDto1 : carDtos) {
            if (Objects.equals(carDto1.getId(), id)) {
                return carDto1;
            }
        }
        return "Car is not found";
    }

    public String update(Long id, CarDto newCar) {
        for (CarDto oldCar : carDtos) {
            if (oldCar.getId().equals(id)) {
                oldCar.setId(newCar.getId());
                oldCar.setModelName(newCar.getModelName());
                oldCar.setYear(newCar.getYear());
            }
        }
        return "Successfully updated";
    }

    public String updateYearById(Long id, Integer year) {
        List<CarDto> list = carDtos.stream().filter(carDto1 -> Objects.equals(carDto1.getId(), id)).toList();

        for (CarDto item : list) {
            item.setYear(year);
        }
        return "Updated";
    }

    public String delete(Long id) {
        carDtos.removeIf(carDto1 -> Objects.equals(carDto1.getId(), id));
        return "Successfully deleted";
    }

    public CarDto convertToDto(CarEntity carEntity) {
        return modelMapper.map(carEntity, CarDto.class);
    }

    public CarEntity convertToDao(CarDto carDto) {
        return modelMapper.map(carDto, CarEntity.class);
    }


}
