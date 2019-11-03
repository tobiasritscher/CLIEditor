import java.util.*;
	
public class Input {
	public List<String> splittext = new ArrayList<>();
	
	private Input(String input) {
		splittext = Arrays.asList(input.split("\\r?\\n"));		
	}
	
	public List<String> getArrayList () {
		return splittext;
	}
	public void numberedParagrap () {
		for(int i = 0; i < splittext.size(); i++) {
			System.out.println((i + 1) + ":" + splittext.get(i));
		}
	}
	
}
