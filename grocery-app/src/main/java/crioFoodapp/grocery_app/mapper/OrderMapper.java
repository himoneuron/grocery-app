package crioFoodapp.grocery_app.mapper;

import crioFoodapp.grocery_app.dto.*;
import crioFoodapp.grocery_app.entity.*;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setStatus(order.getStatus());
        
        orderDto.setOrderItems(order.getOrderItems().stream()
                .map(OrderMapper::mapToOrderItemDto)
                .collect(Collectors.toList()));
        
        return orderDto;
    }

    public static OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setItemId(orderItem.getItem().getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setItemPriceAtOrder(orderItem.getItemPriceAtOrder());
        return dto;
    }
}