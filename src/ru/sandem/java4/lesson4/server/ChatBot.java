package ru.sandem.java4.lesson4.server;

import ru.sandem.java4.lesson4.annotations.BotCommand;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {
    private static final Map<String, Method> commands = new HashMap<>();
    private static final BotCommandHandler commandHandler = new BotCommandHandler();
    //Объект класса с командами (по сути нужен нам для рефлекции)

    static {
        for (Method m : commandHandler.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(BotCommand.class)) {
                BotCommand cmd = m.getAnnotation(BotCommand.class);
                commands.put(cmd.name(), m);
                for (String s : cmd.aliases()) {
                    commands.put(s, m);
                }
            }
        }
    }

    public static void messageToChatBot(String message, ClientHandler clientHandler, Server server) {
        try {
            String[] args = message.split(" ");
            String command = args[1].toLowerCase();
            String[] nArgs = null;
            if (args.length <= 2) {
                nArgs = new String[1];
                nArgs[0] = "_";
            } else {
                nArgs = new String[args.length - 2];
                System.arraycopy(args, 2, nArgs, 0, args.length - 2);
            }
            commandHandler.setClientHandler(clientHandler);
            commandHandler.setServer(server);

            Method m = commands.get(command);
            if (m == null) {
                System.out.println("null");
                return;
            }
            BotCommand com = m.getAnnotation(BotCommand.class);
            if (nArgs.length < com.minArgs()) {
                //что-то если аргументов меньше чем нужно
            } else if (nArgs.length > com.maxArgs()) {
                //что-то если аргументов больше чем нужно
            }
            m.invoke(commandHandler, nArgs[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            //Вывод списка команд или какого-либо сообщения, в случае если просто написать "Бот"
            System.out.println("bot");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
