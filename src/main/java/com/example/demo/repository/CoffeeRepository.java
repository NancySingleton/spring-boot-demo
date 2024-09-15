package com.example.demo.repository;

import com.example.demo.domain.Coffee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CoffeeRepository {
    private final List<Coffee> coffees = new ArrayList<>();

    public CoffeeRepository() {
        coffees.addAll(List.of(
                new Coffee("Latte"),
                new Coffee("Cappuccino"),
                new Coffee("Espresso")
        ));
    }

    public Iterable<Coffee> findAll() {
        return coffees;
    }

    public void save(Coffee coffee) {
        coffees.add(coffee);
    }

    public boolean existsById(String id) {
        return coffees.stream().anyMatch(c -> c.getId().equals(id));
    }

    public void updateNameById(Coffee coffee) {
        coffees.stream()
                .filter(c -> c.getId().equals(coffee.getId()))
                .forEach(c -> c.setName(coffee.getName()));
    }

    public void deleteById(String id) {
        coffees.removeIf(c -> c.getId().equals(id));
    }
}
