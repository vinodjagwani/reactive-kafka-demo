package com.reactive.kafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.kafka.dto.MessagePayload;
import com.reactive.kafka.service.ProducerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UploadMessageController {

    ObjectMapper objectMapper;

    ProducerService producerService;

    @PostMapping
    public ResponseEntity<Void> readFile(@RequestParam("topic") final String topic, @RequestParam("file") final MultipartFile file) throws IOException {
        final MessagePayload[] messagePayloads = objectMapper.readValue(file.getInputStream(), MessagePayload[].class);
        final List<MessagePayload> messagePayloadList = Arrays.asList(messagePayloads);
        log.info("Messages from uploaded file: {}", messagePayloadList);
        producerService.send(topic, messagePayloadList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
