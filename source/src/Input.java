
import java.util.*;

public class Input {
	private List<String> splitText;
	
	public Input(String input) {
		splitText = Arrays.asList(input.split("\n"));
	}
	
	public List<String> getArrayList() {
		return splitText;
	}
}
