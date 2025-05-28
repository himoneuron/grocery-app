package crioFoodapp.grocery_app.service;

import java.util.List;

import crioFoodapp.grocery_app.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderById(Long id);
    List<OrderDto> getOrdersByCustomer(Long customerId);
    OrderDto updateOrderStatus(Long id, String status);
    void cancelOrder(Long id);
}