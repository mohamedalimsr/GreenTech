package com.example.cqrs.command.service;

import com.example.cqrs.command.dto.CreateCostCommand;
import com.example.cqrs.command.dto.UpdateCostCommand;
import com.example.cqrs.command.model.Cost;
import com.example.cqrs.command.repository.CostCommandRepository;
import com.example.cqrs.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CostCommandHandler {
    private final CostCommandRepository costRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "my-kafka-topic";
    @Autowired
    public CostCommandHandler(CostCommandRepository costRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.costRepository = costRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Cost createCost(CreateCostCommand command) {
        // Create a new Cost entity and save it to the database
        Cost cost = new Cost(command.getCostn(), command.getUnit(), command.getVisibility(), command.getThemes());
        kafkaTemplate.send(TOPIC_NAME, "New Cost Created: " + cost.getId());

        return costRepository.save(cost);


    }

    public Cost updateCost(Long id, UpdateCostCommand command) {
        // Retrieve the existing Cost entity from the database
        Cost existingCost = costRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cost not exist with id :" + id));

        // Update the existing Cost entity with the data from the command
        existingCost.setCostn(command.getCostn());
        existingCost.setUnit(command.getUnit());
        existingCost.setVisibility(command.getVisibility());
        existingCost.setThemes(command.getThemes());

        kafkaTemplate.send(TOPIC_NAME, "Cost Updated: " + existingCost.getId());

        // Save the updated Cost entity to the database
        return costRepository.save(existingCost);
    }

    public void deleteCost(Long id) {
        // Retrieve the existing Cost entity from the database
        Cost existingCost = costRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cost not exist with id :" + id));

        kafkaTemplate.send(TOPIC_NAME, "Cost Deleted: " + existingCost.getId());

        // Delete the Cost entity from the database
        costRepository.delete(existingCost);
    }
}

