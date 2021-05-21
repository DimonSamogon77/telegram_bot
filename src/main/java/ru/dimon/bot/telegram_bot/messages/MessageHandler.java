package ru.dimon.bot.telegram_bot.messages;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.dimon.bot.telegram_bot.messages.handlers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandler {
    private Map<String, MessageHandlers> messagesMap = new HashMap<>();

    {
        messagesMap.put("/start", new HelloMessageHandler());
        messagesMap.put("Новость", new NewsHandler());
        messagesMap.put("Новости", new NewsInlineKeyboard());
        messagesMap.put("Крип", new CryptoHandler());
        messagesMap.put("Крипта", new CryptoInlineKeyboard());
        messagesMap.put("Мани", new FinanceHandler());

    }

    public SendMessage messageHandle(Update update) {
        if(update.getMessage().getText().equals("Новости")){
            return messagesMap.get(update.getMessage().getText()).handle(update);
        }else if(update.getMessage().getText().equals("Крипта")){
            return messagesMap.get(update.getMessage().getText()).handle(update);
        }else{
            ReplyKeyboardMarkup replyKeyboardMarkup = getMenuKeyboard();
            return createMessageWithKeyboard(messagesMap.get(update.getMessage().getText()).handle(update), replyKeyboardMarkup);
        }
    }

    public SendMessage callBackHandle(Update update) {
        if(update.getCallbackQuery().getMessage().getText().equals("Выберите новость")){
            return messagesMap.get("Новость").handle(update);
        }else{
            return messagesMap.get("Крип").handle(update);
        }
    }


    private ReplyKeyboardMarkup getMenuKeyboard() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Крипта"));
        row1.add(new KeyboardButton("Новости"));
        row1.add(new KeyboardButton("Мани"));
        keyboard.add(row1);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    private SendMessage createMessageWithKeyboard(SendMessage sendMessage, ReplyKeyboardMarkup keyboardMarkup) {
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }
}
