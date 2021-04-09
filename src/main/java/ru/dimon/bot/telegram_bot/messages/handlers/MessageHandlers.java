package ru.dimon.bot.telegram_bot.messages.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MessageHandlers {
    public SendMessage handle(Update update);
}
