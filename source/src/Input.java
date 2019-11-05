import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);

	public Input() {
    }

	public List<String> readInput(){
        ArrayList<String> splitText = new ArrayList<>();
	    do {
            splitText.add(scanner.nextLine());
	    } while (!splitText.get(splitText.size()-1).equalsIgnoreCase("END") && scanner.hasNextLine());

	    splitText.remove(splitText.size()-1);

	    return splitText;
    }

	public int intIn() {
	    return scanner.nextInt();
    }

	public String stringIn() {
		return scanner.nextLine();
	}
}
