package com.example.demo.service;

import com.example.demo.dto.request.CustomerRequestDto;
import com.example.demo.dto.respons.CustomerResponsDto;
import com.example.demo.entity.CarEntity;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class CustomerService {
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public void create(CustomerRequestDto customerDto) {
        log.info("gelen customer dto entity cevrilir");
//        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);
        CustomerEntity customerEntity = modelMapper.map(customerDto,CustomerEntity.class);
        log.info("customerEntity customer repoya save olunur");
        customerRepository.save(customerEntity);
    }


    public List<CustomerResponsDto> getCars() {
        List<CustomerEntity> all = customerRepository.findAll();
        log.info("customer reposundan butun entityler cekildi");
        return all.stream()
                .map(customerEntity -> modelMapper.map(customerEntity, CustomerResponsDto.class))
                .collect(Collectors.toList());
    }

    public CustomerResponsDto getById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow();
        log.info("customer reposundan id e gore customerEntity tapilir tapmasa throw edecek");
        return modelMapper.map(customerEntity, CustomerResponsDto.class);
    }

    public String update(Long id, CustomerRequestDto newCustomerDto) {
        log.info("customer reposundan id e gore customerEntity tapilir tapmasa throw edecek");
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow();
        modelMapper.map(newCustomerDto, customerEntity);
        log.info("dto parametri entity e cevrilib customer reposuna save edilir");
        customerRepository.save(customerEntity);
        return "Successfully updated";
    }

    public String updateYearById(Long id, String email) {
        log.info("customer reposundan id e gore customerEntity tapilir tapmasa throw edecek");
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow();
        log.info("parametr kimi otrulen email hemin entity nin emailine set olunur");
        customerEntity.setEmail(email);
        return "Email updated";
    }

    public String delete(Long id) {

        log.info("customer reposundan id e gore customerEntity tapilir tapmasa throw edecek");
        customerRepository.findById(id).orElseThrow();
        log.info("eger throw etmese hemin id li obyekti customer reposundan silir");
        customerRepository.deleteById(id);
        return "Successfully deleted";
    }

    public CustomerRequestDto convertToDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerRequestDto.class);
    }

    public CustomerEntity convertToDao(CustomerRequestDto customerDto) {
        return modelMapper.map(customerDto, CustomerEntity.class);
    }


}
