import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handels all the inputs of the programm
 */
public class Input {
    private Scanner scanner;

    /**
     * Constructor of the class Input
     */
    public Input() {
    }

    /**
     * reads the input of the user and saves it in an ArrayList spiltted by '\n'
     *
     * @return ArrayList with the splitted text
     */
    public List<String> readInput() {
        scanner = new Scanner(System.in);
        ArrayList<String> splitText = new ArrayList<>();
        do {
            splitText.add(scanner.nextLine());
        } while (!splitText.get(splitText.size() - 1).equalsIgnoreCase("END") && scanner.hasNextLine());

        splitText.remove(splitText.size() - 1);

        return splitText;
    }

    /**
     * reads a String from the user input
     *
     * @return String
     */
    public String stringIn() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
