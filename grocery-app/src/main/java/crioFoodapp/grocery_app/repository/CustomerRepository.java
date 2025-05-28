package crioFoodapp.grocery_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crioFoodapp.grocery_app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
