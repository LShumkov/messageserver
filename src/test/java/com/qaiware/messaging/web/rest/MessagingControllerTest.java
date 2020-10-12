package com.qaiware.messaging.web.rest;

import com.qaiware.messaging.repository.MessageRepository;
import com.qaiware.messaging.web.MessageController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureWebMvc
public class MessagingControllerTest {


    @Autowired
    private MessageController messageController;

    @MockBean
    private MessageRepository messageRepository;


    @Test
    public void shouldReturnStatusCreated() {
        ResponseEntity<Void> responseEntity = messageController.sendMessage("send_text", "text");
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    public void shouldReturnStatusCreatedForEmotion() {
        ResponseEntity<Void> responseEntity = messageController.sendMessage("send_emotion", "emoticon");
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    public void shouldReturnStatusPreconditionFailed() {
        ResponseEntity<Void> responseEntity = messageController.sendMessage("send_text", "");
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.PRECONDITION_FAILED));
    }

    @Test
    public void shouldReturnStatusBadRequest() {
        ResponseEntity<Void> responseEntity = messageController.sendMessage("send_something", "text");
        assertThat(responseEntity.getStatusCode().equals(HttpStatus.PRECONDITION_FAILED));
    }

    @Test
    public void shouldReturnEmptyBody() {
        ResponseEntity<Void> responseEntity = messageController.sendMessage("send_emotion", ":)");
        assertFalse(responseEntity.hasBody());
    }

    @Test
    public void shouldReturnEmptyBodyOnInvalidInput() {
        ResponseEntity<Void> responseEntity = messageController.sendMessage("send_emotion", "123");
        assertFalse(responseEntity.hasBody());
    }

}
