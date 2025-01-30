package com.insy2s.APIRest_Restaurant.Services;

import com.insy2s.APIRest_Restaurant.Exceptions.DishAlreadyExistsException;
import com.insy2s.APIRest_Restaurant.Exceptions.DishIsInOrderException;
import com.insy2s.APIRest_Restaurant.Exceptions.DishNotFoundException;
import com.insy2s.APIRest_Restaurant.Models.DTO.DishDTO;
import com.insy2s.APIRest_Restaurant.Models.Entities.Dish;
import com.insy2s.APIRest_Restaurant.Models.Mappers.DishMapper;
import com.insy2s.APIRest_Restaurant.Models.Repositories.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DishService {

    private final IDishRepository dishRepository;

    public List<DishDTO> getAllDishes() {

        return dishRepository.findAll()
                .stream()
                .map(DishMapper::toDishDTO)
                .toList();
    }


    public DishDTO getDishById(Long id) {

        Optional<Dish> dish = dishRepository.findById(id);
        return dish
                .map(DishMapper::toDishDTO)
                .orElseThrow(() -> new DishNotFoundException("Il n'existe pas de plat avec cet id"));
    }


    public DishDTO createDish(DishDTO dishDTO) {

        Dish dish = DishMapper.toDish(dishDTO);

        if (dishRepository.existsByName(dish.getName())) {
            throw new DishAlreadyExistsException("Un plat ayant ce nom existe déjà");
        }

        return DishMapper.toDishDTO(dishRepository.save(dish));
    }


    public DishDTO updateDish(Long id, DishDTO dishDTO) {

        Dish dishToUpdate = DishMapper.toDish(dishDTO);

        Dish existingDish = dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException("Il n'existe pas de plat avec cet id"));

        if (dishToUpdate.getName() != null) {
            existingDish.setName(dishDTO.getName());
        }

        if (dishToUpdate.getPrice() != null) {
            existingDish.setPrice(dishDTO.getPrice());
        }

        if (dishRepository.existsByName(dishDTO.getName())) {
            throw new DishAlreadyExistsException("Un plat existe déjà avec ce nom");
        }

        return DishMapper.toDishDTO(dishRepository.save(existingDish));
    }


    public void deleteDishById(Long id) {

        Optional<Dish> dish = dishRepository.findById(id);

        dish.ifPresentOrElse(d -> {
            try {
                dishRepository.delete(d);
            } catch (Exception e) {
                throw new DishIsInOrderException("Vous ne pouvez pas supprimer un plat déjà présent dans une commande");
            }
        }, () -> {
            throw new DishNotFoundException("Il n'existe pas de plat avec cet id : " + id);
        });
    }
}
