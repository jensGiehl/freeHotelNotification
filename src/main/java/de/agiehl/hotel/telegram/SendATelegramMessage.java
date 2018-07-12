package de.agiehl.hotel.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class SendATelegramMessage extends TelegramLongPollingBot {

	@Value("${botName}")
	private String botName;

	@Value("${botToken}")
	private String botToken;

	@Value("${telegramChatId}")
	private long chatId;

	private static final Logger logger = LoggerFactory.getLogger(SendATelegramMessage.class);

	@Override
	public void onUpdateReceived(Update update) {
		// Do nothing ...
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	public void sendMessage(String message) throws TelegramApiException {
		logger.info("Send message to {}", chatId);
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(chatId);
		sendMessage.setText(message);

		sendMessage(sendMessage);
	}

}
