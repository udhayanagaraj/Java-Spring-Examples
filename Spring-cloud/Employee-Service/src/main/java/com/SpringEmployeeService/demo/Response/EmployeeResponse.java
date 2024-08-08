package com.SpringEmployeeService.demo.Response;

import com.SpringEmployeeService.demo.Entity.AddressDTO;

public class EmployeeResponse {
    private int id;
    private String name;
    private String email;
    private String age;
    private AddressDTO addressDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddressResponse(AddressDTO addressResponse) {
        this.addressDTO = addressResponse;
    }

    public AddressDTO getAddressDTO(){
        return addressDTO;
    }

}
