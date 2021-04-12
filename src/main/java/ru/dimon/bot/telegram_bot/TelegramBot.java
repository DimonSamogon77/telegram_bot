package ru.dimon.bot.telegram_bot;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dimon.bot.telegram_bot.messages.Facade;

import java.io.File;

@Getter
@Setter
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private String botUsername;
    private String botToken;
    private Facade facade;

    public TelegramBot(Facade facade){
        this.facade = facade;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasCallbackQuery()){
            execute(facade.getMessageHandler().callBackHandle(update));
        }else {
            String text = update.getMessage().getText();
            switch (text) {
                case "Аудио":
                    execute(sendAudio(update));
                    break;
                case "Фото":
                    execute(sendPhoto(update));
                    break;
                default:
                    execute(facade.getMessageHandler().messageHandle(update));
            }
        }
    }

    public SendAudio sendAudio(Update update){
        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(update.getMessage().getChatId().toString());
        sendAudio.setAudio(new InputFile(new File("src/main/resources/audios/1.mp3")));
        return sendAudio;
    }

    public SendPhoto sendPhoto(Update update){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(update.getMessage().getChatId().toString());
        sendPhoto.setPhoto(new InputFile(new File("src/main/resources/photos/1.jpg")));
        return sendPhoto;
    }
}
