package ru.sandem.java4.lesson4;

import ru.sandem.java4.lesson4.filters.ChairOnlyFilter;
import ru.sandem.java4.lesson4.filters.JavaOnlyFilter;
import ru.sandem.java4.lesson4.server.Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartServer {
    public static final String SERVER_HOST = "localhost"; //127.0.0.1
    public static final int SERVER_PORT = 9933;
    public static final String DB_NAME = "chat.db";

    public static void main(String[] args) {
        Server server = new Server(SERVER_PORT, DB_NAME);
        //чтобы основной поток не "замирал" в этом месте

        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(server::waitForClient);
        service.shutdown();
//        new Thread(server::waitForClient).start();
        server.addFilter(new JavaOnlyFilter());
        server.addFilter(new ChairOnlyFilter());
        //и т.д. - по ходу программы можем добавлять новые фильтры
    }
}
