package Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Execute script.
 */
public class ExecuteScript implements CommandableEx {
    /**
     * The Execute script history.
     */
    static ArrayList<String> exScrHistory = new ArrayList<>();
    /**
     * @param arg the filename
     */

    @Override
    public String execute(Object arg, Socket clientSocket) {
        try {
            exScrHistory.add("execute_script "+(String) arg);
            String result = "";
            File file = new File((String) arg);
            Scanner in = new Scanner(file);
            Commands commands = new Commands();
            String exFileName= "";
            while (in.hasNextLine()) {
                String command=in.nextLine();
                command = command.replace(" ", "\t");
                String[] exArg = command.split("\t");
                if(exArg.length == 2) {
                    exFileName = exArg[1];
                }
                if (!(command.equals(""))) {
                    if (!(command.equals("execute_script " + exFileName))) {
                        result+=("Команда \"" + command + "\":\n");
                        result+=commands.doCommand(command, clientSocket);
                        result+="\n";
                    }
                    else {
                        if (exScrHistory.contains("execute_script " + exFileName)) {
                            result+=("Команда \"" + command + "\": невыполнима во избежание бесконечной рекурсии .\n\n");
                        }
                        else {
                            exScrHistory.add("execute_script " + exFileName);
                            result+=("Выполнение вложенного скрипта \"" + command + "\":\n");
                            result+=commands.doCommand(command, clientSocket);
                            result+="Выполнение вложенного скрипта завершено.\n\n";
                        }
                    }
                }
            }
            exScrHistory.clear();
            return result;
        } catch (NullPointerException | FileNotFoundException e) {
            return ("Указанный файл не найден.");
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
