import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Editor {
    private static List<String> text;
    private static Input input;
    private static Output output;
    private static LoremIpsum loremIpsum;
    private Map<String, Integer> countWords = new HashMap<>();

	public Editor() {
        text = new ArrayList<>();
        input = new Input();
        output = new Output();
        loremIpsum = new LoremIpsum();
	}

	public static void main(String[] args){
	    Editor editor = new Editor();
	    boolean nextOption = true;
        editor.chooseText();
	    do {
	    	editor.chooseOption();
	    	//TODO: Only for debbugging, need better solution
            output.print("Type 'N' to exit application");
            if (input.stringIn().equalsIgnoreCase("N")) nextOption = false;

        } while (nextOption);
	}

	private void chooseText() {
	    output.print("Do you want to use your own text? [Y/N]:");
	    if (input.stringIn().equalsIgnoreCase("Y")) {
            output.print("Please insert your text. At the end of your text switch to a new line and write 'END':");
            text = input.readInput();
        } else text = loremIpsum.getTextArray();
    }

	private void chooseOption() {
	    String[] options = {"1: Print paragraphs", "2: Insert paragraph", "3: Delete paragraph", "4: Replace a text", "5: Index Words",
                "6: Print formated text"};
        output.print("What do you want to do with your text? (Just write the number):");

        //print all the options
        for (int i = 0; options.length - 1 >= i; i++){
            output.print(options[i]);
        }

	    switch (input.intIn()) {
            case 1: output.printNumberedParagrap(text); break;
            case 2: insertParagraph(); break;
            case 3: deleteParagraph(); break;
            case 4: replace(); break;
            case 5: indexWords(); break;
            case 6: formatedText(); break;
            default: output.print("Wrong input, try again!"); break;
	    }
    }

	private void insertParagraph() {
		boolean controllLoop = true;
		int position;

		output.print("Please type the position at which you would like your paragraph to be placed at:");
		do {
			position = input.intIn() - 1;
			if (position < 0 || position > text.size() - 1) {
				output.print("This isn't a valid number. Try again:");
			} else controllLoop = false;
		} while (controllLoop);

		output.print("Now type the desired paragraph:");
		text.add(position, input.stringIn());

		output.printNumberedParagrap(text);
    }

    private void deleteParagraph() {
    	boolean controllLoop = true;
		int position;
    	output.print("Which paragraph would you like to delete?");

    	do {
    		position = input.intIn() - 1;
    		if (position < 0 || position > text.size() - 1)
    			output.print("Please give a valid number!");
    		else controllLoop = false;
    	} while (controllLoop);

    	text.remove(position);

        output.printNumberedParagrap(text);
    	
    }

    private void replace() {
		int paragraph;
		String partOfText;
		String oldWord;
		String newWord;

    	output.print("In which paragraph would you like to replace your word?");
    	paragraph = input.intIn() - 1;
    	partOfText = text.get(paragraph);

    	output.print("What word would you like to replace:");
    	oldWord = input.stringIn();

    	output.print("What word would you like to use instead?");
    	newWord = input.stringIn();
		partOfText = partOfText.replace(oldWord, newWord);

    	text.set(paragraph, partOfText);
    	output.printNumberedParagrap(text);
    }

    private void indexWords() {
        /*
	    TODO: hashmap erstellen mit allen Wörtern, welche öfters als zwei mal vorkommen
        HashMap<Paragraph, word>
        - Zwei For-Loops machen und durch iterieren. Ich benötige einen zweidimensionalen Array
        Wir müssen auch noch wissen, in welchen Paragraphen das Wort vorkommt. Es macht Sinn 
        hierfür eine eigene Methode (oder Klasse?) zu erstellen. 
        */
    	for(int i = 0; i<text.size(); i++) {
			List<String> words = Arrays.asList(text.get(i).split(" "));
		}
		

    }

    private void formatedText() {
	    /*
	    TODO: Formatierte Ausgabe von Text mit einer einstellbaren maximalen Spaltenbreite Zeichen,
        Zeilenumbruch jeweils auf dem letzten Lehrzeichen
        (bei zu langen Wörtern an beliebiger Stelle innerhalb des Wortes)
        */

    }
    
}
