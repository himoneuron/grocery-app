package crioFoodapp.grocery_app.impl;

import org.springframework.stereotype.Service;

import crioFoodapp.grocery_app.dto.CustomerDto;
import crioFoodapp.grocery_app.entity.Customer;
import crioFoodapp.grocery_app.mapper.CustomerMapper;
import crioFoodapp.grocery_app.repository.CustomerRepository;
import crioFoodapp.grocery_app.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        // TODO Auto-generated method stub

        Customer customer = CustomerMapper.mapToCustomer(customerDto);
         // Save to database
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }
    @Override
    public CustomerDto getCustomerById(Long id) {
        // TODO Auto-generated method stub
        Customer customer = customerRepository
        .findById(id).orElseThrow(() -> new RuntimeException("failed to find the customer"));
        return CustomerMapper.mapToCustomerDto(customer);
    }
    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new  RuntimeException("Customer not found with id: " ));
        
        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setAddress(customerDto.getAddress());
        existingCustomer.setPhone(customerDto.getPhone());
        
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return CustomerMapper.mapToCustomerDto(updatedCustomer);
    }
    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: "));
        customerRepository.delete(customer);
    }

}
