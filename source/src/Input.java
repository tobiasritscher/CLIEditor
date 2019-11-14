import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handles all the inputs of the program
 */
public class Input {
    private Scanner scanner;

    /**
     * Constructor of the class Input
     */
    public Input() {
    }

    /**
     * Reads the input of the user and saves it in an ArrayList split by new line ('\n')
     *
     * @return ArrayList with the split text
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
     * Reads string from the user input
     *
     * @return String read by the scanner
     */
    public String stringIn() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
