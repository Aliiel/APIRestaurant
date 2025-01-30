package com.insy2s.APIRest_Restaurant.Controllers;

import com.insy2s.APIRest_Restaurant.Models.DTO.OrderDTO;
import com.insy2s.APIRest_Restaurant.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.findOrdersByCustomerId(customerId));
    }


    @PostMapping("/{customerId}")
    public ResponseEntity<OrderDTO> createOrder(
            @PathVariable Long customerId, @RequestBody List<Long> idDishes) {
        return ResponseEntity.ok(orderService.createOrder(customerId, idDishes));
    }
}
