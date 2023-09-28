package com.example.cqrs.query.service;

import com.example.cqrs.command.model.Cost;
import com.example.cqrs.command.repository.CostCommandRepository;
import com.example.cqrs.query.dto.CostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CostQueryHandler {
    private final CostCommandRepository costRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "query-log-topic";
    @Autowired
    public CostQueryHandler(CostCommandRepository costRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.costRepository = costRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<CostDto> getAllCosts() {
        // Retrieve and map Cost entities to CostDto objects
        List<Cost> costs = costRepository.findAll();
        kafkaTemplate.send(TOPIC_NAME, "User retrieved all costs");
        return costs.stream()
                .map(cost -> new CostDto(cost.getId(), cost.getCostn(), cost.getUnit(), cost.getVisibility(), cost.getThemes()))
                .collect(Collectors.toList());
    }

    public CostDto getCostById(Long id) {
        // Retrieve a specific Cost entity and map it to a CostDto
        Optional<Cost> cost = costRepository.findById(id);
        kafkaTemplate.send(TOPIC_NAME, "User retrieved cost with ID: " + id);
        return cost.map(c -> new CostDto(c.getId(), c.getCostn(), c.getUnit(), c.getVisibility(), c.getThemes()))
                .orElse(null);
    }
    public void sendCustomKafkaMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}

