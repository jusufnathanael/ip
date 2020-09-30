package parser;

import commands.Command;
import exceptions.DukeException;

public class Parser {

    private static String command;
    private static String message;

    public Parser(String line) {
        if (line.contains(" ")) {
            command = line.substring(0, line.indexOf(" "));
        } else {
            command = line;
        }
        message = line;
    }

    public String getCommand() {
        return command;
    }

    public Command parseCommand() throws DukeException {

        switch(command) {
            case Command.LIST:
                return new Command(Command.LIST);
            case Command.DONE:
                return new Command(Command.DONE, message);
            case Command.DELETE:
                return new Command(Command.DELETE, message);
            case Command.CLEAR:
                return new Command(Command.CLEAR);
            case Command.DEADLINE:
                return new Command(Command.DEADLINE, message);
            case Command.EVENT:
                return new Command(Command.EVENT, message);
            case Command.TODO:
                return new Command(Command.TODO, message);
            case Command.FIND:
                return new Command(Command.FIND, message);
            case Command.BYE:
                return new Command(Command.BYE);
            default:
                throw new DukeException();
        }

    }

}
