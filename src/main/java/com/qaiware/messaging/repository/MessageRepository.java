package com.qaiware.messaging.repository;

import com.qaiware.messaging.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
