package Commands;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Commands.
 */
public class Commands {
    private static Map<String, CommandableEx> commands = new TreeMap<>();

    private static Boolean toStop = false;
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
     * @throws IOException
     */
    public Map<CommandableEx, String> doCommand(String commandName) throws IOException {
        String[] nameAndArgument = commandName.split(" ");
        commandName = commandName.replace(" ", "");
        Map<CommandableEx, String> commAndPar = new HashMap();
        if (!commandName.equals("")) {
            if (nameAndArgument[0].equals("exit")){
                commAndPar.put(commands.get(nameAndArgument[0]), null);
                toStop = true;
                return commAndPar;
            }
            if (nameAndArgument[0].equals("help")){
                System.out.println(commands.get(nameAndArgument[0]).execute(null));
                return null;
            }
            else if (commands.get(nameAndArgument[0]) == null) {
                System.out.println("Такой команды не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
                return null;
            } else {
                try {
                    CommandNoArg commandNoArg = (CommandNoArg) commands.get(nameAndArgument[0]);
                    try {
                        String s = nameAndArgument[1];
                        System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        return null;
                    } catch (Exception e) {
                        commAndPar.put(commands.get(nameAndArgument[0]), null);
                        return commAndPar;
                    }
                } catch (Exception e) {
                    try {
                        String s = nameAndArgument[2];
                        System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        return null;
                    } catch (IndexOutOfBoundsException e1) {
                        try {
                            commAndPar.put(commands.get(nameAndArgument[0]),nameAndArgument[1]);
                            return commAndPar;
                        } catch (IndexOutOfBoundsException e2) {
                            System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                            return null;
                        }
                    }
                }
            }
        }
        return null;
    }
}