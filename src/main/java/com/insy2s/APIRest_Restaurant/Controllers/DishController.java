package com.insy2s.APIRest_Restaurant.Controllers;

import com.insy2s.APIRest_Restaurant.Models.DTO.DishDTO;
import com.insy2s.APIRest_Restaurant.Services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishDTO>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.getDishById(id));
    }


    @PostMapping
    public ResponseEntity<DishDTO> createDish(@RequestBody DishDTO dishDTO) {
        return ResponseEntity.ok(dishService.createDish(dishDTO));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<DishDTO> updateDish(@PathVariable Long id, @RequestBody DishDTO dishDTO) {
        return ResponseEntity.ok(dishService.updateDish(id, dishDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDishById(@PathVariable Long id) {
        dishService.deleteDishById(id);
        return ResponseEntity.ok("Plat supprimé avec succès");
    }
}
