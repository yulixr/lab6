

import Manager.CollectionManager;
import Manager.ServerConnection;
import Movie.MovieCollection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;

public class ServerSide {
    private static MovieCollection serverCollection = new MovieCollection(System.getenv("collection"));
    /**
     * Main class
     * @param args
     */
    public static void main(String[] args) {
        serverCollection.getCollection().sort(Comparator.naturalOrder());
        try (ServerSocket server = new ServerSocket(2022)) {
            System.out.print("Сервер начал слушать клиентов. " + "\nПорт " + server.getLocalPort() +
                    " / Адрес " + InetAddress.getLocalHost() + ".\nОжидаем подключения клиентов ");
            Thread pointer = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print(".");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.print("\n");
                        Thread.currentThread().interrupt();
                    }
                }
            });
            pointer.setDaemon(true);
            pointer.start();
            while (true) {
                Socket incoming = server.accept();
                pointer.interrupt();
                System.out.println(incoming + " подключился к серверу.");
                Runnable r = new ServerConnection(serverCollection, incoming);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}