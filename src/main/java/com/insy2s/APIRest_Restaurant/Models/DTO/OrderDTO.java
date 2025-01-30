package com.insy2s.APIRest_Restaurant.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private LocalDateTime orderDate;
    private CustomerDTO customerDTO;
    private List<DishDTO> dishesDTO;
}
