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
import ui.Ui;



public class Storage {

    private static String path;

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
        TaskList tasks = new TaskList();

        while (s.hasNext()) {
            String message = s.nextLine();
            try {
                int done = Integer.parseInt(String.valueOf(message.charAt(1)));
                Parser p = new Parser(decodeLine(message));
                Command c = p.parseCommand();
                c.addReadFile(tasks, done);
            }
            catch (DukeException e) {
                System.out.println("Cannot read file!");
            }
        }

        if (tasks.getTask().size() == 0) {
            System.out.println("Currently there are no tasks in the list.");
        }
        System.out.println(new Ui().DIVIDER);
        return tasks.getTask();
    }

}
