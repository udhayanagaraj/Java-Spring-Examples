package com.SpringEmployeeService.demo.Service;

import com.SpringEmployeeService.demo.Entity.AddressDTO;
import com.SpringEmployeeService.demo.Entity.Employee;
import com.SpringEmployeeService.demo.Repository.EmployeeRepo;
import com.SpringEmployeeService.demo.Response.EmployeeResponse;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ServiceUnavailableException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public EmployeeResponse getEmployeeId(int id){
        Optional<Employee> employeeOptional = employeeRepo.findById(id);

        if (employeeOptional.isEmpty()) {
            throw new NotFoundException("Employee not found with id: " + id);
        }

        Employee employee = employeeOptional.get();

        EmployeeResponse employeeResponse = mapper.map(employee,EmployeeResponse.class);

        ServiceInstance serviceInstance = loadBalancerClient.choose("address-service");

        if(serviceInstance != null) {
            String uri = serviceInstance.getUri().toString();

            String contextPath = serviceInstance.getMetadata().getOrDefault("configPath","");

            String addressUrl = uri + contextPath + "/address/" + id;
            AddressDTO addressResponse = restTemplate.getForObject(addressUrl, AddressDTO.class);

            System.out.println("Address response: "+ addressResponse);
            employeeResponse.setAddressResponse(addressResponse);
        }else{
            throw new ServiceUnavailableException("Address - service is not available");
        }

        return employeeResponse;
    }
}
