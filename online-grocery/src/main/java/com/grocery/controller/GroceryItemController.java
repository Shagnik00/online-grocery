package com.grocery.controller;

import com.grocery.entity.GroceryItem;
import com.grocery.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class GroceryItemController {
    private final GroceryItemService groceryItemService;

    @PostMapping
    public ResponseEntity<GroceryItem> createItem(@RequestBody GroceryItem item) {
        return new ResponseEntity<>(groceryItemService.createItem(item), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllItems() {
        return ResponseEntity.ok(groceryItemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(groceryItemService.getItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateItem(@PathVariable Long id, @RequestBody GroceryItem item) {
        return ResponseEntity.ok(groceryItemService.updateItem(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        groceryItemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
