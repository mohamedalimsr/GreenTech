package com.example.cqrs.command.repository;

import com.example.cqrs.command.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostCommandRepository extends JpaRepository<Cost, Long> {
}
