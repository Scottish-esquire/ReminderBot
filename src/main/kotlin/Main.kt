import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.request.SendMessage

private const val TOKEN = "6269432954:AAGLvy6tfgCRBi2RjL8SvpGeSCMdKiZTibw"

object Main {

    private var bot = TelegramBot(TOKEN)


    @JvmStatic
    fun main(args: Array<String>) {

        startBotListener()
    }

    private fun startBotListener() {
        bot.setUpdatesListener({
            it.forEach {

                val text = it.message().text()
                val chatId = it.message().chat().id()

                when(text){
                    "привет" -> {
                        bot.execute(SendMessage(chatId, "Привет!)"))
                    }
                    else ->
                        bot.execute(SendMessage(chatId, "не понимаю тебя"))
                }

                println()
            }

            UpdatesListener.CONFIRMED_UPDATES_ALL
        },{

            bot.removeGetUpdatesListener()

            bot = TelegramBot(TOKEN)
            startBotListener()
        })
    }
}