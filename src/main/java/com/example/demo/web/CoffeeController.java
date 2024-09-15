package com.example.demo.web;

import com.example.demo.domain.Coffee;
import com.example.demo.dto.CoffeeDto;
import com.example.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @PostMapping
    void postCoffee(@RequestBody CoffeeDto coffeeDto) {
        Coffee coffee = new Coffee(coffeeDto.name());
        coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    void putCoffee(
            @PathVariable String id,
            @RequestBody CoffeeDto coffeeDto
    ) {
        Coffee coffee = new Coffee(id, coffeeDto.name());

        if (coffeeRepository.existsById(id)) {
            coffeeRepository.updateNameById(coffee);
        } else {
            coffeeRepository.save(coffee);
        }
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(
            @PathVariable String id
    ) {
        coffeeRepository.deleteById(id);
    }
}
