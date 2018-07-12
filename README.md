# Is a room free?
A short program which informs me via telegram if a special hotel has free rooms.

## Configuration
 You need to provide some configurations. [See Spring Boot Doc](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) to find out how you can do that.
 
The import configurations are:
* `url` - URL to check
* `botName` - Your Telegram Bot name
* `botToken` - Auth Token for your Telegram Bot
* `telegramChatId` - Telegram Chat-ID

### Check multiple URLs
If you decide to use `application.properties` you can add multiple profiles (eg `application-test.properties` = test profile).
In this profile properties files you can overwrite the settings from your `application.properties`.

To use the profile add `spring.profiles.active` to the command:
```java -jar -Dspring.profiles.active=test HotelChecker-0.0.1-SNAPSHOT.jar```
or
```java -jar HotelChecker-0.0.1-SNAPSHOT.jar --spring.profiles.active=test```



## Create your telegram bot

1. Message @botfather https://telegram.me/botfather with the following
text: `/newbot`
   If you don't know how to message by username, click the search
field on your Telegram app and type `@botfather`, you should be able
to initiate a conversation. Be careful not to send it to the wrong
contact, because some users has similar usernames to `botfather`.

2. @botfather replies with `Alright, a new bot. How are we going to
call it? Please choose a name for your bot.`

3. Type whatever name you want for your bot.

4. @botfather replies with `Good. Now let's choose a username for your
bot. It must end in bot. Like this, for example: TetrisBot or
tetris_bot.`

5. Type whatever username you want for your bot, minimum 5 characters,
and must end with `bot`. For example: `telesample_bot`

6. @botfather replies with:

    Done! Congratulations on your new bot. You will find it at
telegram.me/telesample_bot. You can now add a description, about
section and profile picture for your bot, see /help for a list of
commands.

    Use this token to access the HTTP API:
    <b>123456789:AAG90e14-0f8-40183D-18491dDE</b>

    For a description of the Bot API, see this page:
https://core.telegram.org/bots/api

7. Note down the 'token' mentioned above.

### Get Chat Id
1. Click on the link @botfather provides (e.g. `telegram.me/telesample_bot`).

2. Send any text to the bot.

3. Open `https://api.telegram.org/bot<TOKEN>/getUpdates` in your browser (note: replace `<TOKEN>` with your token (see step 7).

4. In the response you will find the Chat Id