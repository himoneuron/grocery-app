package crioFoodapp.grocery_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import crioFoodapp.grocery_app.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}