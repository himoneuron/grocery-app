package crioFoodapp.grocery_app.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crioFoodapp.grocery_app.dto.OrderDto;
import crioFoodapp.grocery_app.dto.OrderItemDto;
import crioFoodapp.grocery_app.entity.Customer;
import crioFoodapp.grocery_app.entity.GroceryItem;
import crioFoodapp.grocery_app.entity.Order;
import crioFoodapp.grocery_app.entity.OrderItem;
import crioFoodapp.grocery_app.exceptions.ResourceNotFoundException;
import crioFoodapp.grocery_app.mapper.OrderMapper;
import crioFoodapp.grocery_app.repository.CustomerRepository;
import crioFoodapp.grocery_app.repository.GroceryItemRepository;
import crioFoodapp.grocery_app.repository.OrderRepository;
import crioFoodapp.grocery_app.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GroceryItemRepository groceryItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                          CustomerRepository customerRepository,
                          GroceryItemRepository groceryItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        // 1. Validate customer exists
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderDto.getCustomerId()));

        // 2. Pre-validate all items exist
        List<Long> itemIds = orderDto.getOrderItems().stream()
                .map(OrderItemDto::getItemId)
                .collect(Collectors.toList());
        
        List<GroceryItem> items = groceryItemRepository.findAllById(itemIds);
        if (items.size() != orderDto.getOrderItems().size()) {
            throw new ResourceNotFoundException("One or more items not found");
        }

        // 3. Create order
        Order order = new Order();
        order.setCustomer(customer);
        
        // 4. Process order items
        List<OrderItem> orderItems = orderDto.getOrderItems().stream()
                .map(itemDto -> {
                    GroceryItem item = items.stream()
                            .filter(i -> i.getId().equals(itemDto.getItemId()))
                            .findFirst()
                            .orElseThrow();
                    
                    // Check stock availability
                    if (item.getQuantity() < itemDto.getQuantity()) {
                        try {
                            throw new Exception(
                                    "Insufficient stock for item: " + item.getName() +
                                            ". Available: " + item.getQuantity() +
                                            ", Requested: " + itemDto.getQuantity());
                        } catch (Exception ex) {
                        }
                    }
                    
                    // Update item stock
                    item.setQuantity(item.getQuantity() - itemDto.getQuantity());
                    
                    // Create order item
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setItem(item);
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setItemPriceAtOrder(item.getPrice());
                    return orderItem;
                })
                .collect(Collectors.toList());
        
        order.setOrderItems(orderItems);
        
        // 5. Calculate total price
        double totalPrice = orderItems.stream()
                .mapToDouble(oi -> oi.getItemPriceAtOrder() * oi.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);
        
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.mapToOrderDto(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Order not found with id: " + id));
        return OrderMapper.mapToOrderDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        
        if ("CANCELLED".equalsIgnoreCase(status)) {
            // Restore stock if order is cancelled
            order.getOrderItems().forEach(oi -> {
                GroceryItem item = oi.getItem();
                item.setQuantity(item.getQuantity() + oi.getQuantity());
            });
        }
        
        order.setStatus(status.toUpperCase());
        return OrderMapper.mapToOrderDto(order);
    }

    @Override
    public void cancelOrder(Long id) {
        updateOrderStatus(id, "CANCELLED");
    }
}