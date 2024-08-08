package com.springLoadBalancer.demo.controller;

import ch.qos.logback.classic.Logger;
import com.springLoadBalancer.demo.response.AddressResponse;
import com.springLoadBalancer.demo.service.AddressService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
public class AddressController {
    private static final Logger log = (Logger) LoggerFactory.getLogger(AddressController.class);
    @Autowired
    private AddressService addressService;

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId){
        try {
            AddressResponse response = addressService.findAddressByEmployeeId(employeeId);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching address with id {}", employeeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
