package com.qaiware.messaging.web;

import com.qaiware.messaging.enums.MessageType;
import com.qaiware.messaging.service.MessageService;
import com.qaiware.messaging.service.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages/{type}")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@PathVariable String type,  @RequestParam("payload") String message) {

        if(MessageType.TEXT.getType().equals(type) || MessageType.EMOTION.getType().equals(type)) {
            try {
                messageService.sendMessage(type, message);
            } catch (InvalidInputException ex) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
