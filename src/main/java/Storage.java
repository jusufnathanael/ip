import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;


public class Storage {

    private static final String DIVIDER = "-------------------------------------------------------";

    private static String path;
    private static Command c;

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

    public void readFile(ArrayList<Task> t) throws FileNotFoundException {

        File f = new File(path);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String message = s.nextLine();
            c = new Command(decodeLine(message));
            try {
                int done = Integer.parseInt(String.valueOf(message.charAt(1)));
                c.addReadFile(t, done);
            }
            catch (DukeException e){
                System.out.println("Cannot read file!");
            }
        }

        System.out.println(DIVIDER);
    }

}
