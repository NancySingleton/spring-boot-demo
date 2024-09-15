package com.example.demo.web;

import com.example.demo.domain.Coffee;
import com.example.demo.dto.CoffeeDto;
import com.example.demo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void postCoffee(@RequestBody CoffeeDto coffeeDto) {
        Coffee coffee = new Coffee(coffeeDto.name());
        coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> putCoffee(
            @PathVariable String id,
            @RequestBody CoffeeDto coffeeDto
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
