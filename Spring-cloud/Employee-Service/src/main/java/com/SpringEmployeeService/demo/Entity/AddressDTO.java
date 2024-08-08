package com.SpringEmployeeService.demo.Entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private int id;
    private String city;
    private String state;


    @Override
    public String toString(){
        return "AddressDto: {" + "id: "+id+" ,city: "+city+", state:" +state +"}";
    }
}
