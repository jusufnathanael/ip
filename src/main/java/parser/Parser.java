package parser;

import commands.Command;
import exceptions.DukeException;

public class Parser {

    public static Command parseCommand(String message) throws DukeException {

        String command;
        if (message.contains(" ")) {
            command = message.substring(0, message.indexOf(" "));
        } else {
            command = message;
        }

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
            case Command.BYE:
                return new Command(Command.BYE);
            default:
                System.out.println("Command not found!");
                throw new DukeException();
        }

    }

}
