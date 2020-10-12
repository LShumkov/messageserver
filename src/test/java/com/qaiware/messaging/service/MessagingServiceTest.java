package com.qaiware.messaging.service;


import com.qaiware.messaging.repository.MessageRepository;
import com.qaiware.messaging.service.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureWebMvc
public class MessagingServiceTest {

    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    @Test
    public void shouldThrowExceptionOnEmptyInput() {
        assertThrows(InvalidInputException.class, () -> {
            messageService.sendMessage("send_text", "");
        });
        assertThrows(InvalidInputException.class, () -> {
            messageService.sendMessage("send_emotion", "");
        });
    }

    @Test
    public void shouldThrowExceptionOnInvalidInput() {
        assertThrows(InvalidInputException.class, () -> {
            messageService.sendMessage("send_emotion", "6jahsd");
        });
    }

    @Test
    public void shouldPersistValidTextMessage() {
        assertDoesNotThrow(() -> messageService.sendMessage("send_text", "text"));
    }

    @Test
    public void shouldPersistValidEmotionMessage() {
        assertDoesNotThrow(() -> messageService.sendMessage("send_emotion", ":)"));
    }


}
