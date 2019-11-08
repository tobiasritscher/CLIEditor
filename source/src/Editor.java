import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Editor {
    private static List<String> text;
    private static Input input;
    private static Output output;
    private static LoremIpsum loremIpsum;
    private static Map<String, List<Integer>> wordIndex = new TreeMap<String, List<Integer>>();
	

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
            output.print("Please insert your text. At the end of your text switch to a new line and write 'END':");
            text = input.readInput();
        } else {
	        text = loremIpsum.getTextArray();
        }
    }

	private void chooseOption() {
	    String[] options = {"1: Print paragraphs", "2: Insert paragraph", "3: Delete paragraph", "4: Replace a paragraph", "5: Index Words",
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
        - Zwei For-Loops machen und durch iterieren. Ich benötige einen zweidimensionalen Array
        Wir müssen auch noch wissen, in welchen Paragraphen das Wort vorkommt. Es macht Sinn 
        hierfür eine eigene Methode (oder Klasse?) zu erstellen. 
        */
    	System.out.println("Total number of paragraphs: " + text.size());
    	System.out.println("*******************************");
    	
    	
    	
    	
    	for(int i = 0; i<text.size(); i++) {
			List<String> words = Arrays.asList(text.get(i).split(" "));
			List<Integer> paragraphen = new ArrayList<>();
    		
    		for(int p = 0; p<words.size(); p++) {
    			String word = words.get(p).replaceAll("[,.]","");
    			word = word.toLowerCase();
    			
    			if(wordIndex.get(word) == null) {
    				wordIndex.put(word, new ArrayList<Integer>());
    				
    				/*Das erste Element des Arrays zählt die Häufigkeit des Wortes.
    				Hier wird es initialisiert.*/
    				wordIndex.get(word).add(1);
    				
    				/*Das zweite Element des Arrays gibt an, in welchem Paragraph das Wort
    				zum ersten Mal auftritt*/
    				wordIndex.get(word).add(i+1);

    			}else {
    				//wordIndex.put(word,wordFrequency + 1);
    				wordIndex.get(word).set(0, wordIndex.get(word).get(0).intValue() + 1);
    				
    				//Fügt hinzu, in welchem Paragraphen das Wort noch auftritt
    				wordIndex.get(word).add(i+1);
    			}
    		}
		}
		printIndex();

    }
    
    //Speichert in welchem Paragraph sich das gesuchte Wort befindet
    public void indexInParagraph() {
    	
    }
    
    public void printIndex() {
        for (Map.Entry<String, List<Integer>> word : wordIndex.entrySet()){
        	String key = word.getKey();
        	List<Integer> values = word.getValue();
        	
        	//Nur ausgeben, wenn das Wort häufiger als 1mal vorkommt
        	if(values.get(0) > 1) {
        		System.out.print("'" + key + "'");
        		System.out.print(" exists " + values.get(0) + " times in paragraphs [");
        		for(int i=1; i<values.size()-1;i++) {
        			System.out.print(values.get(i) + ", ");
        		}
        		//Print last occurrence of word
        		System.out.println(values.get(values.size()-1) + "]");
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
 