package com.reactive.kafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.kafka.dto.MessagePayload;
import com.reactive.kafka.service.ProducerService;
import com.reactive.kafka.util.MockFileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UploadMessageController.class)
public class UploadMessageControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProducerService producerService;


    @Test
    public void testReadFile() throws Exception {
        final String content = MockFileUtils.getResource("packages.json", String.class);
        MockMultipartFile file = new MockMultipartFile("data", "package.json", "text/plain", content.getBytes());
        objectMapper.readValue(file.getInputStream(), MessagePayload[].class);
        doNothing().when(producerService).send(anyString(), anyList());
        mockMvc.perform(multipart("/upload").file("file", file.getBytes())
                .param("topic", "demo-topic"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
