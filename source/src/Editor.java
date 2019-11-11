import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This Class handels all the editing of the text
 */
public class Editor {
    private static List<String> paragraphs;
    private static Input input;
    private static Output output;
    private static LoremIpsum loremIpsum;
    private static Map<String, List<Integer>> wordIndex = new TreeMap<>();
    private static int ARRAY_OFFSET = 1;

	/**
	 * Constructor of the class Editor
	 */
	public Editor() {
        paragraphs = new ArrayList<>();
        input = new Input();
        output = new Output();
        loremIpsum = new LoremIpsum();
	}

	/**
	 * main method of the programm, here it all begins
	 * @param args arguments passed to the code when exicuting the programm
	 */
	public static void main(String[] args){
	    Editor editor = new Editor();
	    editor.chooseAndSetText();
        editor.callEditingOption();
	}

	private void chooseAndSetText() {
	    output.print("Do you want to use your own text? [Y/N]:");
	    if (input.stringIn().equalsIgnoreCase("Y")) {
            output.print("Please insert your text. At the end of your text switch to a new line and write 'END':");
            paragraphs = input.readInput();
        } else {
	        paragraphs = loremIpsum.getTextArray();
        }
    }

	private void callEditingOption() {
	    String[] options = {"1: Print paragraphs", "2: Insert paragraph", "3: Delete paragraph", "4: Replace a paragraph", "5: Index Words",
                "6: Print formated text"};
        output.print("What do you want to do with your text? (Just write the number):");

        //print all the options
		for (String s : options){
			output.print(s);
		}

		//switch case to choose the option
	    switch (input.intIn()) {
            case 1: output.printNumberedParagrap(paragraphs); break;
            case 2: insertParagraph(); break;
            case 3: deleteParagraph(); break;
            case 4: replace(); break;
            case 5: indexWords(); break;
            case 6: formatedText(); break;
            default: output.print("Wrong input, try again!"); break;
	    }
    }

	private void insertParagraph() {
		output.print("Please type the position at which you would like your paragraph to be placed at:");
		int position = input.intIn() - ARRAY_OFFSET;
		output.print("Now type the desired paragraph:");
		paragraphs.add(position, input.stringIn());
    }

    private void deleteParagraph() {
    	output.print("Which paragraph would you like to delete?");
    	int position = input.intIn() - ARRAY_OFFSET;
    	paragraphs.remove(position);
    }

    private void replace() {
        //TODO: keyword suchen und ersetzen

    }

    private void indexWords() {
    	System.out.println("Total number of paragraphs: " + paragraphs.size());
    	System.out.println("*******************************");
    	
    	
    	for(int i = 0; i < paragraphs.size(); i++) {
			String[] words = paragraphs.get(i).split(" ");

			for (String s : words) {
				String word = s.replaceAll("[,.]", "");
				word = word.toLowerCase();

				if (wordIndex.get(word) == null) {
					wordIndex.put(word, new ArrayList<>());
    				
    				/*
    				Das erste Element des Arrays zählt die Häufigkeit des Wortes.
    				Hier wird es initialisiert.
    				*/
					wordIndex.get(word).add(1);
    				
    				// Das zweite Element des Arrays gibt an, in welchem Paragraph das Wort zum ersten Mal auftritt
					wordIndex.get(word).add(i + 1);

				} else {
					//wordIndex.put(word,wordFrequency + 1);
					wordIndex.get(word).set(0, wordIndex.get(word).get(0) + 1);

					//Fügt hinzu, in welchem Paragraphen das Wort noch auftritt
					wordIndex.get(word).add(i + 1);
				}
			}
		}
		printIndex();
    }

    private void indexInParagraph() {
		//TODO: Speichert in welchem Paragraph sich das gesuchte Wort befindet
    }
    
    private void printIndex() {
        for (Map.Entry<String, List<Integer>> word : wordIndex.entrySet()){
        	String key = word.getKey();
        	List<Integer> values = word.getValue();
        	
        	//Nur ausgeben, wenn das Wort häufiger als ein mal vorkommt
        	if (values.get(0) > 1) {
        		System.out.print("'" + key + "'");
        		System.out.print(" exists " + values.get(0) + " times in paragraphs [");
        		for (int i = 1; i < values.size() - ARRAY_OFFSET;i++) {
        			System.out.print(values.get(i) + ", ");
        		}
        		//Print last occurrence of word
        		System.out.println(values.get(values.size() - ARRAY_OFFSET) + "]");
        	}
        	
        	
        	
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
 