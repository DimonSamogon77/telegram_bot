package ru.dimon.bot.telegram_bot;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dimon.bot.telegram_bot.messages.Facade;

import java.io.File;

@Getter
@Setter
public class TelegramBot extends TelegramLongPollingBot {
    private String botUsername;
    private String botToken;
    private Facade facade;

    public TelegramBot(Facade facade) {
        this.facade = facade;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            execute(facade.getMessageHandler().callBackHandle(update));
        } else {
            execute(facade.getMessageHandler().messageHandle(update));
        }
    }
}

