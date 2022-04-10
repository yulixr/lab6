package Commands;
/**
 * The type Exit.
 */
public class Exit implements CommandNoArg{
    @Override
    public String execute(Object o) {
       return null;
    }

    @Override
    public String getName() {
        return "exit";
    }
}