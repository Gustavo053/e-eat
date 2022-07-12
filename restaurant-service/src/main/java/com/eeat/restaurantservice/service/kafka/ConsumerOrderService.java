package com.eeat.restaurantservice.service.kafka;

import com.eeat.restaurantservice.model.EOrderStatus;
import com.eeat.restaurantservice.model.OrderUser;
import com.eeat.restaurantservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerOrderService {
    @Value("${topic.name.consumer}")
    private String topicName;
    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "orders_id")
    public ConsumerRecord<String, String> consume(ConsumerRecord<String, String> payload) throws Exception {
        log.info("Topic: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order {}", payload.value());

        ObjectMapper objectMapper = new ObjectMapper();
        OrderUser orderUser = objectMapper.readValue(payload.value(), OrderUser.class);

        orderUser.setStatus(EOrderStatus.CREATED);
        orderRepository.save(orderUser);

        return payload;
    }
}
