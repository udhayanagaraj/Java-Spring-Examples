package com.springLoadBalancer.demo.service;

import com.springLoadBalancer.demo.entity.Address;
import com.springLoadBalancer.demo.repository.AddressRepo;
import com.springLoadBalancer.demo.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper mapper;

    public AddressResponse findAddressByEmployeeId(int employeeId){
        Optional<Address> addressByEmployeeId = addressRepo.finalAddressByEmployeeId(employeeId);
        return mapper.map(addressByEmployeeId,AddressResponse.class);
    }
}
