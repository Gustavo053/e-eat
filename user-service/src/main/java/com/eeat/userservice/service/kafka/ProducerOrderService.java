package com.eeat.userservice.service.kafka;

import com.eeat.userservice.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerOrderService {
    @Value("${topic.name.producer}")
    private String topicName;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(Order order) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderStringFormatted = objectMapper.writeValueAsString(order);

        kafkaTemplate.send(topicName, orderStringFormatted);
        log.info("Order sended: {}", orderStringFormatted);
    }
}
