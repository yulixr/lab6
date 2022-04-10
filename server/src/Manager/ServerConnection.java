package Manager;

import Commands.*;
import Movie.MovieCollection;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс Server Connection
 */
public class ServerConnection implements Runnable {

    private MovieCollection serverCollection;
    private Socket incoming;
    Commands commands = new Commands();

    /**
     * @param incoming активное соединение с клиентской программой.
     */
    public ServerConnection(MovieCollection serverCollection, Socket incoming) {
        this.serverCollection = serverCollection;
        this.incoming = incoming;
        commands.regist(new Exit(), new Help(), new Info(), new Insert(), new Show(), new Update(),
                new RemoveKey(), new RemoveGreater(), new RemoveLower(), new RemoveByMpaa(),
                new CountOscars(), new ShowOscarsElements(), new Save(), new Clear(),
                new ExecuteScript());
    }

    /**
     * Запускает активное соединение с клиентом
     */
    @Override
    public void run() {
        try (ObjectInputStream getFromClient = new ObjectInputStream(incoming.getInputStream());
             ObjectOutputStream sendToClient = new ObjectOutputStream(incoming.getOutputStream())) {
            sendToClient.writeObject("Соединение установлено.\nВы можете вводить команды. help для справки");
            while (true) {
                try {
                    String requestFromClient = (String) getFromClient.readObject();
                    System.out.print("Получено [" + requestFromClient + "] от " + incoming + ". ");
                    String[] parsedCommand = requestFromClient.trim().split("\t",2);
                    if (parsedCommand.length == 1)
                        sendToClient.writeObject(commands.doCommand(requestFromClient, incoming));
                    else if (parsedCommand.length == 2)
                        sendToClient.writeObject(commands.doCommand(requestFromClient, incoming));
                    System.out.println("Ответ успешно отправлен.");
                } catch (SocketException e) {
                    System.out.println(incoming + " отключился от сервера.");
                    serverCollection.save();
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(incoming + " отключился от сервера.");
        }
    }

    @Override
    public String toString() {
        return "ServerConnection{" +
                ", incoming=" + incoming +
                ", availableCommands=" + commands +
                '}';
    }
}