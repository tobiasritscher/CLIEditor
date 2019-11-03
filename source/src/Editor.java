import java.util.*;

public class Editor {
	private Map<Integer, String> paragraphs = new HashMap<>();
	private Input input;
	public Editor() {
		for(int i = 0; i < input.splittext.size(); i++) {
			paragraphs.put( i + 1, input.splittext.get(i));
		}
	}
}
