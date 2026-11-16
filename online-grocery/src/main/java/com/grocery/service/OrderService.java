package com.grocery.service;

import com.grocery.dto.OrderRequest;
import com.grocery.entity.Customer;
import com.grocery.entity.GroceryItem;
import com.grocery.entity.Order;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CustomerRepository;
import com.grocery.repository.GroceryItemRepository;
import com.grocery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GroceryItemRepository groceryItemRepository;

    public Order createOrder(OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<GroceryItem> items = groceryItemRepository.findAllById(request.getGroceryItemIds());
        if (items.isEmpty() || items.size() != request.getGroceryItemIds().size()) {
            throw new ResourceNotFoundException("One or more grocery items not found");
        }

        double totalPrice = items.stream().mapToDouble(GroceryItem::getPrice).sum();

        Order order = new Order();
        order.setCustomer(customer);
        order.setGroceryItems(items);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() { return orderRepository.findAll(); }
    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public void deleteOrder(Long id) { orderRepository.delete(getOrderById(id)); }
}
