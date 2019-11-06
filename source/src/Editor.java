import java.util.ArrayList;
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
	    editor.chooseText();
        editor.chooseOption();
	}

	private void chooseText() {
	    output.print("Do you want to use your own text? [Y/N]:");
	    if (input.stringIn().equalsIgnoreCase("Y")) {
            output.print("Please insert your text. At the end of your text write ''END'':");
            text = input.readInput();
        } else {
	        text = loremIpsum.getTextArray();
        }
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
	    //TODO: paragraph einsetzen
		output.print("Please type the position at which you would like your paragraph to be placed at:");
		int position = input.intIn() - 1;
		output.print("Now type the desired paragraph:");
		text.add(position, input.stringIn());
    }

    private void deleteParagraph() {
	    //TODO: paragraph löschen
    	output.print("Which paragraph would you like to delete?");
    	int position = input.intIn() - 1;
    	text.remove(position);
    }

    private void replace() {
        //TODO: keyword suchen und ersetzen

    }

    private void indexWords() {
        /*
	    TODO: hashmap erstellen mit allen Wörtern, welche öfters als zwei mal vorkommen
        HashMap<Paragraph, word>
        */
  

    }

    private void formatedText() {
	    /*
	    TODO: Formatierte Ausgabe von Text mit einer einstellbaren maximalen Spaltenbreite Zeichen,
        Zeilenumbruch jeweils auf dem letzten Lehrzeichen
        (bei zu langen Wörtern an beliebiger Stelle innerhalb des Wortes)
        */

    }
}
