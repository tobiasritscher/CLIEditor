
import java.util.List;

public class Output {

	/**
	 * handels all print methodes from the programm
	 */
	public Output() {
	}

	/**
	 * prints the text sorted by paragraphs and numbered in the console
	 * 
	 * @param array List to print
	 */
	public void printNumberedParagrap(List<String> array) {
		for (int i = 0; i < array.size(); i++) {
			System.out.println((i + 1) + ": " + array.get(i));
		}
	}

	/**
	 * prints a String in the console
	 * 
	 * @param string a String to print
	 */
	public void print(String string) {
		System.out.println(string);
	}
}
