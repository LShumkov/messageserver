package com.qaiware.messaging.service;

import com.qaiware.messaging.service.exceptions.InvalidInputException;

public interface IMessageService {

    void sendMessage(String type, String message) throws InvalidInputException;

}
