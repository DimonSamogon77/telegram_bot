package ru.dimon.bot.telegram_bot.messages.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CryptoInlineKeyboard implements MessageHandlers{
    @Override
    public SendMessage handle(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        for(int i = 0; i<8; i++){
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setCallbackData(String.valueOf(i));
            inlineKeyboardButton.setText(String.valueOf(i+1));
            row1.add(inlineKeyboardButton);
        }
        for(int i = 8; i<10; i++){
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setCallbackData(String.valueOf(i));
            inlineKeyboardButton.setText(String.valueOf(i+1));
            row2.add(inlineKeyboardButton);
        }
        rows.add(row1);
        rows.add(row2);

        inlineKeyboardMarkup.setKeyboard(rows);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setText("Выберите");
        return sendMessage;
    }
}
