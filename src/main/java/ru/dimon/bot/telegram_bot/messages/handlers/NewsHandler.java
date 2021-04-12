package ru.dimon.bot.telegram_bot.messages.handlers;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

@Component
public class NewsHandler implements MessageHandlers {


    @Override
    public SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        sendMessage.setText(getNews(update.getCallbackQuery().getData()));
        return sendMessage;
    }

    @SneakyThrows
    public String getNews(String i){
        URL url = new URL("https://newsapi.org/v2/top-headlines?country=ru&apiKey=407b368ecaa545819f1191eebe12e479");
        int index = Integer.parseInt(i);
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while(scanner.hasNext()){
            result+=scanner.nextLine();
        }
        JSONObject jsonObject = new JSONObject(result);;
        JSONArray jsonArray = jsonObject.getJSONArray("articles");
        return jsonArray.getJSONObject(index).getString("title")+"\n"+jsonArray.getJSONObject(index).getString("url");
    }

}
