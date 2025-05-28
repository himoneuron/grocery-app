package crioFoodapp.grocery_app.mapper;

import crioFoodapp.grocery_app.dto.CustomerDto;
import crioFoodapp.grocery_app.entity.Customer;

public class CustomerMapper {


    //// Convert DTO to Entity
    public static Customer mapToCustomer(CustomerDto customerDto) {
        
        return new Customer(
            customerDto.getId(),
            customerDto.getName(),
            customerDto.getEmail(),
            customerDto.getAddress(),
            customerDto.getPhone()
        );
    }
    
    // Convert Entity to DTO
    //public static CustomerDto mapToCustomerDto(Customer customer) {
        // Convert Entity to DTO
    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getPhone()
        );
    }
}

