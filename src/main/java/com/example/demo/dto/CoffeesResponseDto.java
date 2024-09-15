package com.example.demo.dto;

import com.example.demo.domain.Coffee;

public record CoffeesResponseDto(Iterable<Coffee> coffees) {
}
