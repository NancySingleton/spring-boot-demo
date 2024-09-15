package com.example.demo.web;

import com.example.demo.domain.Coffee;
import com.example.demo.dto.CoffeeRequestDto;
import com.example.demo.dto.CoffeesResponseDto;
import com.example.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private final CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;

        coffeeRepository.saveAll(List.of(
                new Coffee("Latte"),
                new Coffee("Cappuccino"),
                new Coffee("Espresso")
        ));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    CoffeesResponseDto getCoffees() {
        Iterable<Coffee> coffees = coffeeRepository.findAll();
        return new CoffeesResponseDto(coffees);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postCoffee(@RequestBody CoffeeRequestDto coffeeDto) {
        Coffee coffee = new Coffee(coffeeDto.name());
        coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> putCoffee(
            @PathVariable String id,
            @RequestBody CoffeeRequestDto coffeeDto
    ) {
        Coffee coffee = new Coffee(id, coffeeDto.name());

        if (coffeeRepository.existsById(id)) {
            coffeeRepository.updateNameById(coffee);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            coffeeRepository.save(coffee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCoffee(
            @PathVariable String id
    ) {
        coffeeRepository.deleteById(id);
    }
}
