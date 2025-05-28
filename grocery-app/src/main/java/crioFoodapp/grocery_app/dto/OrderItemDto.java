package crioFoodapp.grocery_app.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long itemId;
    private Integer quantity;
    private Double itemPriceAtOrder;
}