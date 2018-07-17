package de.agiehl.hotel.notification;

import java.util.prefs.Preferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class NotificationStatus {

	private static final String LAST_NOTIFICATION_STATUS = "lastNotificationStatus";

	private static final Logger logger = LoggerFactory.getLogger(NotificationStatus.class);

	@Autowired
	private Environment environment;

	public boolean setStatus(boolean newStatus) {
		Preferences preferences = Preferences.userRoot().node("hotelChecker" + getProfiles());
		logger.info("Node name is {}", preferences.name());

		boolean oldStatus = preferences.getBoolean(LAST_NOTIFICATION_STATUS, false);
		logger.info("Old status is {} (new status is {})", oldStatus, newStatus);

		if (oldStatus != newStatus) {
			preferences.putBoolean(LAST_NOTIFICATION_STATUS, newStatus);
			logger.info("Set status to {}", newStatus);
			return true;
		}

		return false;
	}

	private String getProfiles() {
		StringBuilder str = new StringBuilder();
		for (String profile : environment.getActiveProfiles()) {
			str.append("-").append(profile);
		}

		return str.toString();
	}

}
