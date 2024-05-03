package com.example.demo.service;

import com.example.demo.dto.request.CarRequestDto;
import com.example.demo.dto.respons.CarResponsDto;
import com.example.demo.entity.CarEntity;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarService {
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final JavaMailSender javaMailSender;
//    private MimeMailMessage mimeMailMessage;

    public CarService(ModelMapper modelMapper, CarRepository carRepository, CustomerRepository customerRepository, JavaMailSender javaMailSender /*, MimeMailMessage mimeMailMessage */) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.javaMailSender = javaMailSender;
//        this.mimeMailMessage = mimeMailMessage;
    }


    public void create(CarRequestDto carDto) {
        CarEntity carEntity = new CarEntity();
//        CarEntity carEntity = modelMapper.map(carDto, CarEntity.class);
//        CarEntity carEntity = modelMapper.map(carDto, CarEntity.class);
        log.info("gelen dto entity e cevrildi");
        carEntity.setCarYear(carDto.getCarYear());
        carEntity.setModelName(carDto.getModelName());

        Long customerId = carDto.getCustomerId();
        log.info("gonderilen customer id tapildi");
        log.info("customer repodan hemin idli object tapilir");
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow();
        log.info("carEntity nin customerEntitysine tapilan object menimsedilir");
        carEntity.setCustomerEntity(customerEntity);
        log.info("carEntity car repoya save edilir.");
        carRepository.save(carEntity);
    }


    public List<CarResponsDto> getCars() {
        List<CarEntity> all = carRepository.findAll();
        log.info("car reposundan butun entityler cekildi");
        return all.stream()
                .map(carEntity -> modelMapper.map(carEntity, CarResponsDto.class))
                .collect(Collectors.toList());
    }

    public CarEntity getById(Long id) {
        log.info("car reposundan id e gore car entity cagrilir tapmasa throw edecek");
        return carRepository.findById(id).orElseThrow();
    }

    public String update(Long id, CarRequestDto newCarDto) {
        log.info("car reposundan id e gore car entity cagrilir tapmasa throw edecek");
        CarEntity carEntity = carRepository.findById(id).orElseThrow();
        modelMapper.map(newCarDto, carEntity);
        log.info("dto parametri entity e cevrilib car reposuna save edilir");
        carRepository.save(carEntity);
        return "Successfully updated";
    }

    public String updateYearById(Long id, Integer year) {
        log.info("car reposundan id e gore car entity cagrilir tapmasa throw edecek");
        CarEntity carEntity = carRepository.findById(id).orElseThrow();
        log.info("parametr kimi otrulen il hemin entity nin year ne set olnur");
        carEntity.setCarYear(year);
        return "Updated";
    }

    public String delete(Long id) {
        log.info("car reposundan id e gore car entity cagrilir tapmasa throw edecek");
        carRepository.findById(id).orElseThrow();
        log.info("eger throw etmese hemin id li obyekti car reposundan silir");
        carRepository.deleteById(id);
        return "Successfully deleted";
    }

    public String sendEmail(String sub,String text){
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo("mamedovt850@gmail.com");
        msg.setFrom("tmamedov6344@gmail.com");
        msg.setSubject(sub);
        msg.setText(text);

        javaMailSender.send(msg);


        return "Successfully sent";
    }

    public void sendImage(){

    }


}
