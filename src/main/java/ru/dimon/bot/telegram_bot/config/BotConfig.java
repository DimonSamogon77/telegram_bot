package ru.dimon.bot.telegram_bot.config;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.dimon.bot.telegram_bot.TelegramBot;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String botUsername;
    private static final String botToken = System.getenv("BOT_TOKEN");


    @Bean
    public TelegramBotsApi MySuperTelegramBot(){
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBot telegramBot = new TelegramBot();
            telegramBot.setBotToken(botToken);
            telegramBot.setBotUsername(botUsername);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
        return telegramBotsApi;
    }
}
