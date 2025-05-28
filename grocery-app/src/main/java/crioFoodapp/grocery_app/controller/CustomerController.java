package crioFoodapp.grocery_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crioFoodapp.grocery_app.dto.CustomerDto;
import crioFoodapp.grocery_app.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
private CustomerService customerService;

public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
}
//add account rest api 
@PostMapping
public ResponseEntity<CustomerDto>addCustomer(@RequestBody CustomerDto customerDto){
    return new ResponseEntity<>(customerService.createCustomer(customerDto),HttpStatus.CREATED);
}

//get customer rest api 
@GetMapping("/{id}")
public ResponseEntity<CustomerDto>getCustomerbyId(@PathVariable Long id){
    CustomerDto customerDto = customerService.getCustomerById(id);
    return ResponseEntity.ok(customerDto);
}

 @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


     @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

}
