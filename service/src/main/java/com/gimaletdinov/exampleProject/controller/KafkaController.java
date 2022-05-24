package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.kafka.OrganizationObjectDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static com.gimaletdinov.exampleProject.config.KafkaTopicConfig.KAFKA_GROUP_ID;
import static com.gimaletdinov.exampleProject.config.KafkaTopicConfig.KAFKA_TOPIC_NAME;

@RestController
@RequestMapping("api/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, OrganizationObjectDto> kafkaTemplate;

    @Autowired
    private OrganizationService organizationService;

    private Map<Integer, String> messagesFromKafka = new HashMap<>();
    private Integer messageId = 0;

    @PostMapping
    public ObjectSuccessResponseDto putMessageToKafkaTopic(@Valid @RequestBody OrganizationSaveRequestDto organizationSaveRequestDto) {
        messageId++;
        OrganizationObjectDto organizationObjectDto = new OrganizationObjectDto();
        organizationObjectDto.setId(messageId);
        organizationObjectDto.setOrganizationSaveRequestDto(organizationSaveRequestDto);

        kafkaTemplate.send(KAFKA_TOPIC_NAME, organizationObjectDto);

        ObjectSuccessResponseDto responseDto = new ObjectSuccessResponseDto();
        responseDto.setResult("Success. Wait your data with id = " + messageId);
        return responseDto;
    }

    @GetMapping("/{id}")
    public ObjectSuccessResponseDto getMessageAndDataById(@PathVariable Integer id) {
        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();

        if (messagesFromKafka.containsKey(id)) {
            objectSuccessResponseDto.setResult(messagesFromKafka.get(id));
        } else {
            objectSuccessResponseDto.setResult("Data with id = " + id + " have not proceed yet. Wait.");
        }
        return objectSuccessResponseDto;
    }

    @KafkaListener(
            topics = KAFKA_TOPIC_NAME,
            groupId = KAFKA_GROUP_ID,
            containerFactory = "listenerFactoryBean")
    public void getObjectsFromKafkaTopicAfterSaveObjectAndPutSavedDataToMap(OrganizationObjectDto organizationObjectDto) {
        Organization organization = organizationService.saveOrganization(organizationObjectDto.getOrganizationSaveRequestDto());
        messagesFromKafka.put(organizationObjectDto.getId(), "Your data saved successful : " + organization);
    }
}
