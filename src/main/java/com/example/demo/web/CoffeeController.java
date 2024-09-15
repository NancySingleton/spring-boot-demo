package com.example.demo.web;

import com.example.demo.domain.Coffee;
import com.example.demo.dto.CoffeeDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoffeeController {
    private List<Coffee> coffees = new ArrayList<>();

    public CoffeeController() {
        coffees.addAll(List.of(
                new Coffee("Latte"),
                new Coffee("Cappuccino"),
                new Coffee("Espresso")
        ));
    }

    @GetMapping("/coffees")
    List<Coffee> getCoffees() {
        return coffees;
    }

    @PostMapping("/coffees")
    void postCoffee(@RequestBody CoffeeDto coffeeDto) {
        Coffee coffee = new Coffee(coffeeDto.name());
        coffees.add(coffee);
    }
}
