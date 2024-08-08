package com.springLoadBalancer.demo.repository;

import com.springLoadBalancer.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address,Integer>{
    @Query(
            nativeQuery = true,
            value = "SELECT ea.id,ea.city,ea.state FROM EurakaLoadBalancer.address ea join EurakaLoadBalancer.employee e on e.id = ea.employee_id where ea.employee_id =:employeeId")
            Optional<Address> finalAddressByEmployeeId(@Param("employeeId") int employeeId);

}
