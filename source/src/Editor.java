import java.util.*;

public class Editor {
	private Map<Integer, String> paragraphs = new HashMap<>();
	private Input input;

	public Editor() {
		for(int i = 0; i < input.getArrayList().size(); i++) {
			paragraphs.put(i + 1, input.getArrayList().get(i));
		}
	}

	public static void main(String[] args){
		//MARK: only for testing
		String randomString = "Das Projekt isch voll geil\nIch freu mich scho uf Siedler\nWer das liest ist doof!";
		Input textInput = new Input(randomString);
		textInput.printNumberedParagrap();

		//TODO: Real Code
	}
}
