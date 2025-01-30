package com.insy2s.APIRest_Restaurant.Models.Mappers;

import com.insy2s.APIRest_Restaurant.Models.DTO.DishDTO;
import com.insy2s.APIRest_Restaurant.Models.Entities.Dish;

public class DishMapper {

    public static DishDTO toDishDTO(Dish dish) {

        if (dish == null) {
            return null;
        }

        DishDTO dishDTO = new DishDTO();
        dishDTO.setName(dish.getName());
        dishDTO.setPrice(dish.getPrice());
        return dishDTO;
    }


    public static Dish toDish(DishDTO dishDTO) {
        if (dishDTO == null) {
            return null;
        }

        Dish dish = new Dish();
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        return dish;
    }
}
