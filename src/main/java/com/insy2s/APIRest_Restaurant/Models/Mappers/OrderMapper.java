package com.insy2s.APIRest_Restaurant.Models.Mappers;

import com.insy2s.APIRest_Restaurant.Models.DTO.CustomerDTO;
import com.insy2s.APIRest_Restaurant.Models.DTO.DishDTO;
import com.insy2s.APIRest_Restaurant.Models.DTO.OrderDTO;
import com.insy2s.APIRest_Restaurant.Models.Entities.Customer;
import com.insy2s.APIRest_Restaurant.Models.Entities.Dish;
import com.insy2s.APIRest_Restaurant.Models.Entities.Order;

import java.util.List;

public class OrderMapper {

    public static OrderDTO toOrderDTO(Order order) {

        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderDate(order.getOrderDate());

        CustomerDTO customerDTO = CustomerMapper.toCustomerDTO(order.getCustomer());
        orderDTO.setCustomerDTO(customerDTO);

        List<DishDTO> dishesDTO = order.getDishes()
                .stream()
                .map(DishMapper::toDishDTO)
                .toList();

        orderDTO.setDishesDTO(dishesDTO);

        return orderDTO;
    }

    public static Order toOrder(OrderDTO orderDTO) {

        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());

        Customer customer = CustomerMapper.toCustomer(orderDTO.getCustomerDTO());
        order.setCustomer(customer);

        List<Dish> dishes = orderDTO.getDishesDTO()
                .stream()
                .map(DishMapper::toDish)
                .toList();

        order.setDishes(dishes);

        return order;
    }
}
