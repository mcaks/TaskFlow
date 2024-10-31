package com.example.ToDo_App.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDo_App.model.ToDo;

import java.util.List;

@Repository
public interface IToDoRepo extends JpaRepository<ToDo, Long>{
    List<ToDo> findByStatus(String status);
}