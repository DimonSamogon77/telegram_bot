package ru.dimon.bot.telegram_bot.messages.handlers;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CryptoHandler implements MessageHandlers {
    @Override
    public SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        sendMessage.setText(getInfo(update.getCallbackQuery().getData()));
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    @SneakyThrows
    private String getInfo(String str) {
        Document doc = Jsoup.connect("https://www.tradingview.com/markets/cryptocurrencies/prices-all/")
                .userAgent("Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36")
                .referrer("https://www.tradingview.com/markets/cryptocurrencies/prices-all/")
                .get();
        String result = "";
        Elements list = doc.select("#js-screener-container > div.tv-screener__content-pane > table").select("tr");
        switch (str) {
            case "0":
                for (int i = 1; i < 16; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "1":
                for (int i = 16; i < 31; i++) {
                    result = getString(result, list, i);

                }
                break;
            case "2":
                for (int i = 31; i < 46; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "3":
                for (int i = 46; i < 61; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "4":
                for (int i = 61; i < 76; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "5":
                for (int i = 76; i < 91; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "6":
                for (int i = 91; i < 106; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "7":
                for (int i = 106; i < 121; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "8":
                for (int i = 121; i < 136; i++) {
                    result = getString(result, list, i);
                }
                break;
            case "9":
                for (int i = 136; i < 151; i++) {
                    result = getString(result, list, i);
                }
                break;
        }
        return result;
    }

    private String getString(String result, Elements list, int i) {
        String[] res = list.get(i).text().split(" ");
        int index;
        if (res.length == 11) {
            result += "*" + res[1] + " " + res[2] + " " + res[3] + "*";
            index = 3;
        } else if (res.length == 10) {
            result += "*" + res[0] + " " + res[1] + " " + res[2] + "*";
            index = 2;
        } else if (res.length == 9) {
            result += "*" + res[0] + " " + res[1] + "*";
            index = 1;
        } else {
            result += "*" + res[0] + "*";
            index = 0;
        }
        result += (": \n*MKT CAP:*" + res[1 + index]
                + " \n*LAST:*" + res[3 + index]
                + " \n*TRADED VOL:*" + res[6 + index]
                + " \n*CHG:*" + res[7 + index] + "\n\n");
        return result;
    }
}
