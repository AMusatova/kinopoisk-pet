package pet.project.bot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import pet.project.bot.config.BotConfig;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config) {
        this.config = config;

    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText.substring(0,1)){
                case "/":
                    sendMessage(chatId, "Скоро тут будет команда");
                    break;
                default:
                    sendMessage(chatId, "Простите, я не понимаю");
            }
        }
    }

    private void sendMessage(long chatId, String stringToSend) {
        SendMessage message = new SendMessage(String.valueOf(chatId), stringToSend);
        try {
            execute(message);
        } catch (TelegramApiException e){
        }
    }


}
