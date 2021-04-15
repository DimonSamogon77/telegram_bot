package ru.dimon.bot.telegram_bot.messages.handlers;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FinanceHandler implements MessageHandlers{

    @Override
    public SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(getInfo());
        return sendMessage;
    }

    @SneakyThrows
    private String getInfo(){
        Document doc = Jsoup.connect("https://ru.tradingview.com/markets/indices/quotes-major/")
                .userAgent("Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36")
                .referrer("https://ru.tradingview.com/markets/indices/quotes-major/")
                .get();

        Elements list = doc.select("#js-screener-container > div.tv-screener__content-pane").select("tbody").select("tr");
        String res="";
        for(Element el: list){
            res+=el.text()+"\n\n";
        }
        return res;
    }
}
