package crioFoodapp.grocery_app.mapper;

import crioFoodapp.grocery_app.dto.GroceryItemDto;
import crioFoodapp.grocery_app.entity.GroceryItem;

public class GroceryItemMapper {
    public static GroceryItem mapToGroceryItem(GroceryItemDto itemDto) {
        return new GroceryItem(itemDto.getId(), itemDto.getName(), itemDto.getCategory(),itemDto.getPrice(), itemDto.getQuantity());
    }
    public static GroceryItemDto mapToGroceryItemDto(GroceryItem item) {
        return new GroceryItemDto(
                item.getId(),
                item.getName(),
                item.getCategory(),
                item.getPrice(),
                item.getQuantity()
        );
    }

}