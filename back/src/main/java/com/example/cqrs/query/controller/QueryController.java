package com.example.cqrs.query.controller;

import com.example.cqrs.query.dto.CostDto;
import com.example.cqrs.query.service.CostQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/queries")
public class QueryController {
    private final CostQueryHandler costQueryHandler;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "query-log-topic";
    @Autowired
    public QueryController(CostQueryHandler costQueryHandler, KafkaTemplate<String, String> kafkaTemplate) {
        this.costQueryHandler = costQueryHandler;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/costs")
    public List<CostDto> getAllCosts() {
        kafkaTemplate.send(TOPIC_NAME, "User retrieved all costs");

        return costQueryHandler.getAllCosts();
    }

    @GetMapping("/costs/{id}")
    public CostDto getCostById(@PathVariable Long id) {
        kafkaTemplate.send(TOPIC_NAME, "User retrieved cost with ID: " + id);

        return costQueryHandler.getCostById(id);
    }
}

