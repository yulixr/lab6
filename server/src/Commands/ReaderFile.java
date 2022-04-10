package Commands;

import java.io.*;

/**
 * The type File reader.
 */
public class ReaderFile {
    //private static final String envPath = System.getenv("collection");
    private static final String envPath=System.getenv("PATH");
    /**
     * Get file path string.
     *
     * @return the string
     */
    public static String getFilePath(){
        return envPath;
    }

    /**
     * Read from file string.
     *
     * @param filename the filename
     * @return the string
     */
    public static String readFromFile(String filename)  {
        try {
            String data = "";
            int i;
            File file = new File(filename);
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file));

            BufferedReader reader = new BufferedReader(streamReader);
            while ((i = reader.read())!=-1){
                data+=(char)i;
            }
            return data;
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("Вы забыли указать имя файла.");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
