package com.insy2s.APIRest_Restaurant.Services;

import com.insy2s.APIRest_Restaurant.Exceptions.CustomerNotFoundException;
import com.insy2s.APIRest_Restaurant.Exceptions.DishNotFoundException;
import com.insy2s.APIRest_Restaurant.Exceptions.OrderNotFoundException;
import com.insy2s.APIRest_Restaurant.Exceptions.OrderWithNoDishException;
import com.insy2s.APIRest_Restaurant.Models.DTO.CustomerDTO;
import com.insy2s.APIRest_Restaurant.Models.DTO.DishDTO;
import com.insy2s.APIRest_Restaurant.Models.DTO.OrderDTO;
import com.insy2s.APIRest_Restaurant.Models.Entities.Customer;
import com.insy2s.APIRest_Restaurant.Models.Entities.Dish;
import com.insy2s.APIRest_Restaurant.Models.Entities.Order;
import com.insy2s.APIRest_Restaurant.Models.Mappers.CustomerMapper;
import com.insy2s.APIRest_Restaurant.Models.Mappers.DishMapper;
import com.insy2s.APIRest_Restaurant.Models.Mappers.OrderMapper;
import com.insy2s.APIRest_Restaurant.Models.Repositories.ICustomerRepository;
import com.insy2s.APIRest_Restaurant.Models.Repositories.IDishRepository;
import com.insy2s.APIRest_Restaurant.Models.Repositories.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final IOrderRepository orderRepository;
    private final CustomerService customerService;
    private final DishService dishService;
    private final ICustomerRepository customerRepository;
    private final IDishRepository dishRepository;


    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toOrderDTO)
                .toList();
    }


    public OrderDTO getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order
                .map(OrderMapper::toOrderDTO)
                .orElseThrow(() -> new OrderNotFoundException("Pas de commande trouvée"));
    }


    public OrderDTO createOrder(Long customerId, List<Long> idDishes) {

        Order order = new Order();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Le client n'est pas enregistré"));

        order.setCustomer(customer);
        System.out.println("Client associé à la commande : " + order.getCustomer().getName());

        LocalDateTime now = LocalDateTime.now();
        order.setOrderDate(now);

        if (idDishes.isEmpty()) {
            throw new OrderWithNoDishException("Votre commande doit contenir au moins un plat");
        }

        List<Dish> dishesToAddToOrder = new ArrayList<>();

        for (Long id : idDishes) {
            Dish dish = dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException("Le plat demandé n'existe pas"));
            dishesToAddToOrder.add(dish);
        }

        order.setDishes(dishesToAddToOrder);

        return OrderMapper.toOrderDTO(orderRepository.save(order));
    }


    public List<OrderDTO> findOrdersByCustomerId(Long customerId) {

        List<Order> orders = orderRepository.findByCustomerId(customerId);

        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(OrderMapper.toOrderDTO(order));
        }
        return orderDTOs;
    }
}
