package com.grocery.service;

import com.grocery.entity.GroceryItem;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.GroceryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceryItemService {
    private final GroceryItemRepository repository;

    public GroceryItem createItem(GroceryItem item) { return repository.save(item); }
    public List<GroceryItem> getAllItems() { return repository.findAll(); }
    public GroceryItem getItemById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
    public GroceryItem updateItem(Long id, GroceryItem data) {
        GroceryItem existing = getItemById(id);
        existing.setName(data.getName());
        existing.setCategory(data.getCategory());
        existing.setPrice(data.getPrice());
        existing.setQuantity(data.getQuantity());
        return repository.save(existing);
    }
    public void deleteItem(Long id) { repository.delete(getItemById(id)); }
}
