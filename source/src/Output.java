import java.util.List;

/**
 * Handles all print methods used in the program
 *
 * @author ritsctob
 * @version 1.0
 */
public class Output {

    public Output() {
    }

    /**
     * Prints the text sorted by paragraphs and numbers it accordingly
     *
     * @param array List to print
     */
    public void printNumberedParagraph(List<String> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println((i + 1) + ": " + array.get(i));
        }
    }

    /**
     * Prints a string to the console
     *
     * @param string a string to print
     */
    public void print(String string) {
        System.out.println(string);
    }
}
