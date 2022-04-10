package Manager;

import Commands.*;
import Movie.Movie;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс Manager.ClientConnection
 */
public class ClientConnection {

    private static Scanner fromKeyboard;
    private static ObjectOutputStream toServer;
    private static ObjectInputStream fromServer;

    /**
     * Устанавливает активное соединение с сервером.
     */
    public void work() {
        try (Scanner scanner = new Scanner(System.in)) {
            fromKeyboard = scanner;
            while (true) {
                try (Socket outcoming = new Socket(InetAddress.getLocalHost(), 2022)) {
                    outcoming.setSoTimeout(5000);
                    try (ObjectOutputStream outputStream = new ObjectOutputStream(outcoming.getOutputStream());
                         ObjectInputStream inputStream = new ObjectInputStream(outcoming.getInputStream())) {
                        toServer = outputStream;
                        fromServer = inputStream;
                        interactiveMode();
                        System.out.println("Завершение программы.");
                    }
                } catch (IOException e) {
                    System.err.println("Нет связи с сервером. Подключться ещё раз (введите {да} или {нет})?");
                    String answer;
                    while (!(answer = fromKeyboard.nextLine()).equals("да")) {
                        switch (answer) {
                            case "":
                                break;
                            case "нет":
                                exit();
                                break;
                            default: System.out.println("Введите корректный ответ.");
                        }
                    }
                    System.out.print("Подключение ...");
                    continue;
                }
            }
        }
    }

    /**
     * осуществляет обмен данными с сервером.
     * @throws IOException при отправке и получении данных
     */
    private void interactiveMode() throws IOException {
        try {
            System.out.println((String) fromServer.readObject());
            String command;
            while (!(command = fromKeyboard.nextLine()).equals("exit")) {
                String[] parsedCommand = command.trim().split(" ", 2);
                switch (parsedCommand[0]) {
                    case "":
                        break;
                    case "add":
                        MovieInserter inserter = new MovieInserter();
                        Movie movie = inserter.create("free");
                        StringBuilder result = new StringBuilder();
                        XmlMapper mapper = new XmlMapper();
                        mapper.registerModule(new JavaTimeModule());
                        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                        mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
                        result.append("add").append("\t").append(mapper.writeValueAsString(movie));//.replace(" ", ""));
                        String res = result.toString();
                        toServer.writeObject(res);
                        System.out.println((String) fromServer.readObject());
                        break;
                    case "update":
                        MovieInserter inserter1 = new MovieInserter();
                        movie = inserter1.create(parsedCommand[1]);
                        StringBuilder result1 = new StringBuilder();
                        XmlMapper mapper1 = new XmlMapper();
                        mapper1.registerModule(new JavaTimeModule());
                        mapper1.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                        mapper1.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
                        result1.append("update").append("\t").append(mapper1.writeValueAsString(movie));//.replace(" ", ""));
                        String res1 = result1.toString();
                        toServer.writeObject(res1);
                        System.out.println((String) fromServer.readObject());
                        break;
                    case "import":
                        try {
                            toServer.writeObject(importingFile(parsedCommand[1]));
                            System.out.println((String) fromServer.readObject());
                        } catch (FileNotFoundException e) {
                            System.out.println("Файл по указанному пути не найден.");
                        } catch (SecurityException e) {
                            System.out.println("Файл защищён от чтения.");
                        } catch (IOException e) {
                            System.out.println("Что-то не так с файлом.");
                        }
                        break;
                    default:
                        command = command.replace(" ", "\t");
                        toServer.writeObject(command);
                        System.out.println((String) fromServer.readObject());
                }
            }
            exit();
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден: " + e.getMessage());
        }
    }

    /**
     * Импортирует файл и отправляет на сервер.
     * @param path путь к файлу.
     * @return команду для сервера и содержимое файла.
     */
    private String importingFile(String path) throws SecurityException, IOException {
        File localCollection = new File(path);
        String extension = localCollection.getAbsolutePath().substring(localCollection.getAbsolutePath().lastIndexOf(".") + 1);
        if (!localCollection.exists() | localCollection.length() == 0  | !extension.equals("xml"))
            throw new FileNotFoundException();
        if (!localCollection.canRead()) throw new SecurityException();
        try (BufferedReader inputStreamReader = new BufferedReader(new FileReader(localCollection))) {
            String nextLine;
            StringBuilder result = new StringBuilder();
            while ((nextLine = inputStreamReader.readLine()) != null) result.append(nextLine);
            return "import " + result.toString();
        }
    }

    /**
     * Отвечает за завершение работу клиентского приложения.
     */
    private void exit() {
        System.out.println("Завершение программы.");
        System.exit(0);
    }
}