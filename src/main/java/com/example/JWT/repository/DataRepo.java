package com.example.JWT.repository;

import com.example.JWT.model.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepo extends JpaRepository<DataModel, Integer> {
}
