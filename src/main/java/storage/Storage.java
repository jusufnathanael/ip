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


/**
 * Represents the file used to store the tasks.
 */
public class Storage {

    private static String path;

    /**
     * @throws InvalidFilePathException if the input path is invalid
     * @param filePath filePath from the user
     */
    public Storage(String filePath) throws InvalidFilePathException {
        path = filePath;
        if (!path.endsWith(".txt")) {
            throw new InvalidFilePathException();
        }
    }

    /**
     * Encodes all the tasks into String before writing to the storage file.
     *
     * @param tasks current list of tasks
     * @return String consisting all the encoded tasks
     */
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

    /**
     * Saves the data to the storage file.
     *
     * @throws IOException if there were errors when storing the data to the file
     * @param t current list of tasks
     */
    public void writeToFile(ArrayList<Task> t) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(encodeList(t));
        fw.close();
    }

    /**
     * Decodes the data from the storage file into the same format as the correct user input
     *
     * @param message one line from the storage file
     * @return formatted input string
     */
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

    /**
     * Checks whether the storage file exist or not
     */
    public void checkFile() throws IOException {
        File dir = new File("data");
        if (dir.mkdir()){
            System.out.println("New directory created: " + dir.getName());
        }
        File f = new File("data/data.txt");
        if (f.createNewFile()) {
            System.out.println("New file created: " + f.getName());
        } else {
            System.out.println("Loaded from the file: " + f.getName());
        }
    }

    /**
     * Loads the data from the storage file to a new TaskList
     *
     * @throws FileNotFoundException if the storage file is not found
     * @return the loaded list of tasks
     */
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
