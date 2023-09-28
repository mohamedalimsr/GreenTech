package com.example.cqrs.command.controller;

import com.example.cqrs.command.dto.CreateCostCommand;
import com.example.cqrs.command.dto.UpdateCostCommand;
import com.example.cqrs.command.model.Cost;
import com.example.cqrs.command.service.CostCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/commands")
public class CommandController {
    private final CostCommandHandler costCommandHandler;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "cost-commands-topic"; // Define the Kafka topic for cost commands

    @Autowired
    public CommandController(CostCommandHandler costCommandHandler, KafkaTemplate<String, String> kafkaTemplate) {
        this.costCommandHandler = costCommandHandler;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/costs")
    public Cost createCost(@RequestBody CreateCostCommand command) {
        Cost createdCost = costCommandHandler.createCost(command);

        kafkaTemplate.send(TOPIC_NAME, "Cost created with ID: " + createdCost.getId());

        return costCommandHandler.createCost(command);
    }

    @PutMapping("/costs/{id}")
    public Cost updateCost(@PathVariable Long id, @RequestBody UpdateCostCommand command) {
        Cost updatedCost = costCommandHandler.updateCost(id, command);
        kafkaTemplate.send(TOPIC_NAME, "Cost updated with ID: " + updatedCost.getId());
        return costCommandHandler.updateCost(id, command);
    }

    @DeleteMapping("/costs/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCost(@PathVariable Long id) {
        costCommandHandler.deleteCost(id);
        kafkaTemplate.send(TOPIC_NAME, "Cost deleted with ID: " + id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
