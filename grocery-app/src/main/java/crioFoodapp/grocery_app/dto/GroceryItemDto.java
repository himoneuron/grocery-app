package crioFoodapp.grocery_app.dto;

import lombok.Data;

@Data
public class GroceryItemDto {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer quantity;

    // No-args constructor
    public GroceryItemDto() {}

    // All-args constructor
    public GroceryItemDto(Long id, String name, String category, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}