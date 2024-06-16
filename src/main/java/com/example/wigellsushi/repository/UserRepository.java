package com.example.wigellsushi.repository;

import com.example.wigellsushi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>{

}
