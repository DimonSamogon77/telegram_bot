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
        Document doc = Jsoup.connect("https://www.tradingview.com/markets/indices/quotes-major/")
                .userAgent("Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36")
                .referrer("https://www.tradingview.com/markets/indices/quotes-major/")
                .get();

        Elements list = doc.select("#js-screener-container > div.tv-screener__content-pane").select("tbody").select("tr");
        String result="";
        for(Element el:list) {
            int index = getIndex(el);
            String[] res = el.text().split(" ");
            result +=res[0]+
                    ":\nLast:" + res[index] +
                    "\nCHG%:" + res[index + 1] +
                    "\nCHG:" + res[index + 2] +
                    "\nMax:" + res[index + 3] +
                    "\nMin:" + res[index + 4] +
                    "\nRTG:" + res[res.length-1]+"\n\n";
        }
        System.out.println(result);
        return result;
    }

    private int getIndex(Element element){
        String[] res = element.text().split(" ");
        if(res[res.length-2].equals("Strong")){
            return res.length-7;
        } else return res.length-6;
    }
}
