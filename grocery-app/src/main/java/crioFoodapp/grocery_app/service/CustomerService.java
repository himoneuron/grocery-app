package crioFoodapp.grocery_app.service;

import crioFoodapp.grocery_app.dto.CustomerDto;

public interface CustomerService 
{
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto getCustomerById(Long id);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);

}
