package com.example.wigellsushi.repository;



import com.example.wigellsushi.model.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Integer> {
}
