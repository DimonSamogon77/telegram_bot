package ru.dimon.bot.telegram_bot.messages;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Facade {
    private MessageHandler messageHandler;

    @Autowired
    public Facade(MessageHandler messageHandler){
        this.messageHandler = messageHandler;
    }
}
