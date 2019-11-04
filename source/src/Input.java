import org.jetbrains.annotations.NotNull;

import java.util.*;
	
public class Input {
	private List<String> splittext = new ArrayList<>();
	
	public Input(String input) {
		splittext = Arrays.asList(input.split("\n"));
	}
	
	public List<String> getArrayList() {
		return splittext;
	}

	public void printNumberedParagrap() {
		for(int i = 0; i < splittext.size(); i++) {
			System.out.println((i + 1) + ": " + splittext.get(i));
		}
	}
}
