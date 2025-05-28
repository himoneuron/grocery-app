package crioFoodapp.grocery_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import crioFoodapp.grocery_app.entity.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    // Custom query methods
    List<GroceryItem> findByCategory(String category);
    List<GroceryItem> findByNameContainingIgnoreCase(String name);
}