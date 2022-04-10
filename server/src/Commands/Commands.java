package Commands;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Commands.
 */
public class Commands {
    private static Map<String, CommandableEx> commands = new TreeMap<>();

    /**
     * Gets command.
     *
     * @param commandname the commandname
     * @return the command
     */
    public CommandableEx getCommand(String commandname) {
        return commands.get(commandname);
    }

    /**
     * Sets commands.
     *
     * @param commands the commands
     */
    public static void setCommands(Map<String, CommandableEx> commands) {
        Commands.commands = commands;
    }

    /**
     * Regist.
     *
     * @param commands the commands
     */
    public void regist(CommandableEx... commands) {
        for (CommandableEx command : commands)
            Commands.commands.put(command.getName(), command);
    }

    /**
     * Do command string.
     *
     * @param commandName the command name
     * @return the string
     */
    public String doCommand(String commandName, Socket clientSocket) {
        String[] nameAndArgument = commandName.split("\t");
        commandName = commandName.replace(" ", "");
        if (!commandName.equals("")) {
            if (commands.get(nameAndArgument[0]) == null) {
                return ("Такой команды не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
            } else {
                try {
                    CommandNoArg commandNoArg = (CommandNoArg) commands.get(nameAndArgument[0]);
                    try {
                        String s = nameAndArgument[1];
                        return ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                    } catch (Exception e) {
                        return  commands.get(nameAndArgument[0]).execute(null, clientSocket);
                    }
                } catch (Exception e) {
                    try {
                        String s = nameAndArgument[2];
                        return ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                    } catch (IndexOutOfBoundsException e1) {
                        try {
                            return commands.get(nameAndArgument[0]).execute(nameAndArgument[1], clientSocket);
                        } catch (IndexOutOfBoundsException | FileNotFoundException e2) {
                            return ("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}