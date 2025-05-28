package crioFoodapp.grocery_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crioFoodapp.grocery_app.dto.GroceryItemDto;
import crioFoodapp.grocery_app.service.GroceryItemService;

@RestController
@RequestMapping("/api/items")
public class GroceryItemController {

    private final GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @PostMapping
    public ResponseEntity<GroceryItemDto> createItem(@RequestBody GroceryItemDto itemDto) {
        GroceryItemDto savedItem = groceryItemService.createItem(itemDto);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItemDto> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(groceryItemService.getItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<GroceryItemDto>> getAllItems() {
        return ResponseEntity.ok(groceryItemService.getAllItems());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<GroceryItemDto>> getItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(groceryItemService.getItemsByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItemDto> updateItem(
            @PathVariable Long id,
            @RequestBody GroceryItemDto itemDto) {
        return ResponseEntity.ok(groceryItemService.updateItem(id, itemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        groceryItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}