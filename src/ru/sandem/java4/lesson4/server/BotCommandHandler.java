package ru.sandem.java4.lesson4.server;

import ru.sandem.java4.lesson4.annotations.BotCommand;

import java.lang.reflect.Method;

public class BotCommandHandler {

    private volatile ClientHandler clientHandler = null;
    private volatile Server server = null;

    @BotCommand(name = "привет", args = "", desc = "Будь культурным, поздоровайся",
            aliases = {"здаров"})
    public void hello(String[] args) {
        //Какой-то функционал, на Ваше усмотрение.
//        System.out.println(args);
        new Thread(new MessagesSender("CHATBOT!___Привет, друг!", clientHandler, server)).start();
    }

    @BotCommand(name = "пока", args = "", desc = "", aliases = {"удачи"})
    public void bye(String[] args) {
        // Функционал
        new Thread(new MessagesSender("CHATBOT!___Пока, друг!", clientHandler, server)).start();
    }

    @BotCommand(name = "помощь", args = "", desc = "Выводит список команд", aliases = {"help", "команды"})
    public void help(String[] args)
    {
        StringBuilder sb = new StringBuilder("Список команд: \n");
        for (Method m : this.getClass().getDeclaredMethods())
        {
            if (m.isAnnotationPresent(BotCommand.class))
            {
                BotCommand com = m.getAnnotation(BotCommand.class);
                if (com.showInHelp()) //Если нужно показывать команду в списке.
                {
                    sb.append("Бот, ").
                            append(com.name()).
                            append(" ").
                            append(com.args()).
                            append(" - ").
                            append(com.desc()).
                            append("\n");
                }
            }
        }
        new Thread(new MessagesSender("CHATBOT!___" + sb, clientHandler, server)).start();
    }

    @BotCommand(name = "посчитай", args = "целое число", desc = "Посчитать квадрат числа", aliases = {"калькулятор"})
    public void calc(String[] args) {
        new Thread(new MessagesSender("CHATBOT!___Квадрат числа " + Integer.valueOf(args[0]) + " = "
                + Integer.valueOf(args[0]) * Integer.valueOf(args[0]), clientHandler, server)).start();
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
