package com.example.demo.web;

import com.example.demo.domain.Coffee;
import com.example.demo.dto.CoffeeRequestDto;
import com.example.demo.dto.CoffeesResponseDto;
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
        boolean alreadyExists = coffeeRepository.existsById(id);

        Coffee coffee = new Coffee(id, coffeeDto.name());
        coffeeRepository.save(coffee);

        return new ResponseEntity<>(alreadyExists ? HttpStatus.OK : HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCoffee(
            @PathVariable String id
    ) {
        coffeeRepository.deleteById(id);
    }
}
