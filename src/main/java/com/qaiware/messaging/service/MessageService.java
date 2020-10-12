package com.qaiware.messaging.service;

import com.qaiware.messaging.entity.Message;
import com.qaiware.messaging.enums.MessageType;
import com.qaiware.messaging.repository.MessageRepository;
import com.qaiware.messaging.service.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    private static final String REGEX_NO_DIGITS = "^([^0-9]*)$";
    private static final int MIN_MESSAGE_LENGTH = 1;
    private static final int MAX_TEXT_LENGTH = 160;
    private static final int MAX_EMOTION_LENGTH = 10;

    @Override
    public void sendMessage(String type, String message) throws InvalidInputException {

        MessageType messageType = MessageType.get(type);

        if(isValidInput(messageType, message)) {
            Message messageEntity = new Message(messageType.name(), message);
            messageRepository.save(messageEntity);
        } else {
            throw new InvalidInputException("Payload of type " + type + " not matching constraints." );
        }

    }

    private boolean isValidInput(MessageType type, String message) {

        int messageLength = message.length();

        switch (type) {
            case TEXT:
                return validateTextMessage(messageLength);
            case EMOTION:
                return validateEmotionMessage(message, messageLength);
            default:
                return false;
        }

    }

    private boolean validateTextMessage(int messageLength) {

        if(messageLength >= MIN_MESSAGE_LENGTH && messageLength <= MAX_TEXT_LENGTH) {
            return true;
        }

        return false;
    }

    private boolean validateEmotionMessage(String message, int messageLength) {

        if(messageLength >= MIN_MESSAGE_LENGTH
            && messageLength <= MAX_EMOTION_LENGTH
            && message.matches(REGEX_NO_DIGITS)) {
            return true;
        }

        return false;

    }

}
