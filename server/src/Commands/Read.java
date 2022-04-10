package Commands;

import java.io.IOException;
import java.net.Socket;

/**
 * The type Read.
 */
public class Read implements CommandNoArg {
    @Override
    public String execute(Object o, Socket clientSocket) throws IOException {
        String s = ReaderFile.getFilePath();
        ReaderFile.readFromFile(s);
        return ("");
    }

    @Override
    public String getName() {
        return "read";
    }
}