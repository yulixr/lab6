package Commands;

import java.io.IOException;
import java.net.Socket;

/**
 * The interface Commandable for commands with arguments.
 */
public interface CommandableEx {

    /**
     * Execute string.
     *
     * @param o the o
     * @return the string
     * @throws IOException the io exception
     */
    String execute(Object o, Socket clientSocket) throws IOException;

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();
}
