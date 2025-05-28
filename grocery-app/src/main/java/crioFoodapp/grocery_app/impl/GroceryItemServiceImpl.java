package crioFoodapp.grocery_app.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import crioFoodapp.grocery_app.dto.GroceryItemDto;
import crioFoodapp.grocery_app.entity.GroceryItem;
import crioFoodapp.grocery_app.mapper.GroceryItemMapper;
import crioFoodapp.grocery_app.repository.GroceryItemRepository;
import crioFoodapp.grocery_app.service.GroceryItemService;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    public GroceryItemServiceImpl(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public GroceryItemDto createItem(GroceryItemDto itemDto) {
        GroceryItem item = GroceryItemMapper.mapToGroceryItem(itemDto);
        GroceryItem savedItem = groceryItemRepository.save(item);
        return GroceryItemMapper.mapToGroceryItemDto(savedItem);
    }

    @Override
    public GroceryItemDto getItemById(Long id) {
        GroceryItem item = groceryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        return GroceryItemMapper.mapToGroceryItemDto(item);
    }

    @Override
    public List<GroceryItemDto> getAllItems() {
        return groceryItemRepository.findAll().stream()
                .map(GroceryItemMapper::mapToGroceryItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroceryItemDto> getItemsByCategory(String category) {
        return groceryItemRepository.findByCategory(category).stream()
                .map(GroceryItemMapper::mapToGroceryItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroceryItemDto updateItem(Long id, GroceryItemDto itemDto) {
        GroceryItem existingItem = groceryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        
        existingItem.setName(itemDto.getName());
        existingItem.setCategory(itemDto.getCategory());
        existingItem.setPrice(itemDto.getPrice());
        existingItem.setQuantity(itemDto.getQuantity());
        
        GroceryItem updatedItem = groceryItemRepository.save(existingItem);
        return GroceryItemMapper.mapToGroceryItemDto(updatedItem);
    }

    @Override
    public void deleteItem(Long id) {
        groceryItemRepository.deleteById(id);
    }
}