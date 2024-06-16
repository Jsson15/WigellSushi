package com.example.wigellsushi.repository;

import com.example.wigellsushi.model.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenyRepository extends JpaRepository<Dishes, Integer> {

}