package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DukeException;
import exceptions.InvalidFilePathException;

import commands.Command;
import parser.Parser;
import tasks.Task;
import tasks.TaskList;



public class Storage {

    private static final String DIVIDER = "-------------------------------------------------------";

    private static String path;
    private static Command c;
    private static TaskList tasks;


    public Storage(String filePath) throws InvalidFilePathException {
        path = filePath;
        if (!path.endsWith(".txt")) {
            throw new InvalidFilePathException();
        }
    }

    public String encodeList(ArrayList<Task> tasks) {
        StringBuilder line = new StringBuilder();
        for (Task t: tasks){
            char type = t.toString().charAt(1);
            int done = t.getIsDone();
            String message = t.toString().substring(7);
            line.append(type).append(done).append(" ").append(message).append(System.lineSeparator());
        }
        return line.toString();
    }

    public void writeToFile(ArrayList<Task> t) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(encodeList(t));
        fw.close();
    }

    public String decodeLine(String message) {
        StringBuilder returnMessage = new StringBuilder();
        if (message.startsWith("T")) {
            returnMessage.append("todo ");
        } else if (message.startsWith("E")) {
            returnMessage.append("event ");
        } else if (message.startsWith("D")) {
            returnMessage.append("deadline ");
        }
        return returnMessage.append(message.substring(3)).toString();
    }

    public ArrayList<Task> load() throws FileNotFoundException {

        File f = new File(path);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        tasks = new TaskList();

        while (s.hasNext()) {
            String message = s.nextLine();
            try {
                int done = Integer.parseInt(String.valueOf(message.charAt(1)));
                Command c = Parser.parseCommand(decodeLine(message));
                c.addReadFile(tasks, done);
            }
            catch (DukeException e){
                System.out.println("Cannot read file!");
            }
        }

        System.out.println(DIVIDER);
        return tasks.getTask();
    }

}