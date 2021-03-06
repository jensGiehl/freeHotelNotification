package de.agiehl.hotel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.starter.EnableTelegramBots;

import de.agiehl.hotel.notification.NotificationStatus;
import de.agiehl.hotel.telegram.SendATelegramMessage;

@SpringBootApplication
@EnableTelegramBots
public class Application implements CommandLineRunner {

	@Value("${url}")
	private String url;

	@Value("${siteShouldContainsCssClass:js-reservation-button}")
	private String shouldContains;

	@Value("${siteShouldNotContainsCssClass:sorry_header}")
	private String shouldNotContains;

	@Value("${telegramMessage:Hotel ist verfügbar!}")
	private String messageGoodNews;

	@Value("${telegramMessage:Hotel NICHT verfügbar! :(}")
	private String messageBadNews;

	@Autowired
	private SendATelegramMessage bot;

	@Autowired
	private NotificationStatus status;

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info("Open URL {}", url);
		Document doc = Jsoup.connect(url).get();
		logger.info("Site '{}' found.", doc.title());

		boolean necessaryCssClassIsPresent = !doc.getElementsByClass(shouldContains).isEmpty();
		boolean unnecessaryCssClassIsPresent = !doc.getElementsByClass(shouldNotContains).isEmpty();

		logger.info("Necessary CSS class '{}' is present?: {}", shouldContains, necessaryCssClassIsPresent);
		logger.info("Unnecessary CSS class '{}' is present?: {}", shouldNotContains, unnecessaryCssClassIsPresent);

		boolean isARoomAvaliable = necessaryCssClassIsPresent && !unnecessaryCssClassIsPresent;
		if (status.setStatus(isARoomAvaliable)) {
			if (isARoomAvaliable) {
				bot.sendMessage(messageGoodNews + "\n\n" + url);
			} else {
				bot.sendMessage(messageBadNews + "\n\n" + doc.title());
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
