package ru.dimon.bot.telegram_bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.dimon.bot.telegram_bot.TelegramBot;
import ru.dimon.bot.telegram_bot.messages.Facade;

@Getter
@Setter
@Configuration
public class BotConfig {
    @Value("${telegrambot.botUsername}")
    private String botUsername;
    @Value("${telegrambot.token}")
    private String botToken;


    @Bean
    public TelegramBotsApi MySuperTelegramBot(Facade facade){
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot telegramBot = new TelegramBot(facade);
            telegramBot.setBotToken(botToken);
            telegramBot.setBotUsername(botUsername);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
        return telegramBotsApi;
    }
}
