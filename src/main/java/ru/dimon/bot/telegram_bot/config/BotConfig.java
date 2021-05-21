package ru.dimon.bot.telegram_bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String botUsername;
    private static final String botToken = "1750927599:AAGEqfduEq5Mm22r0_edSPKyjkTdKofSV1k";


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
