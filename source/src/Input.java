import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
	Scanner scanner;

	public Input() {
    }

	public List<String> readInput(){
		scanner = new Scanner(System.in);
        ArrayList<String> splitText = new ArrayList<>();
	    do {
            splitText.add(scanner.nextLine());
	    } while (!splitText.get(splitText.size()-1).equalsIgnoreCase("END") && scanner.hasNextLine());

	    splitText.remove(splitText.size()-1);

	    return splitText;
    }

	public int intIn() {
		scanner = new Scanner(System.in);
	    return scanner.nextInt();
    }

	public String stringIn() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}
