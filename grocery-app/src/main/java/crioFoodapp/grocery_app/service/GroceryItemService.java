package crioFoodapp.grocery_app.service;

import crioFoodapp.grocery_app.dto.GroceryItemDto;
import java.util.List;

public interface GroceryItemService {
    GroceryItemDto createItem(GroceryItemDto itemDto);
    GroceryItemDto getItemById(Long id);
    List<GroceryItemDto> getAllItems();
    List<GroceryItemDto> getItemsByCategory(String category);
    GroceryItemDto updateItem(Long id, GroceryItemDto itemDto);
    void deleteItem(Long id);
}