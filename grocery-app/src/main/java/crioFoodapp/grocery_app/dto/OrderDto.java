package crioFoodapp.grocery_app.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long customerId;
    private List<OrderItemDto> orderItems;
    private LocalDateTime orderDate;
    private Double totalPrice;
    private String status;
}