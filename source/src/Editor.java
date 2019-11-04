import java.util.ArrayList;
import java.util.List;

public class Editor {
    private static List<String> text;
    private static Input input;
    private static Output output;

	public Editor() {
        text = new ArrayList<>();
        input = new Input();
        output = new Output();
	}

	public static void main(String[] args){
	    Editor editor = new Editor();
        text = input.readInput();

        editor.chooseOption();
	}

	private void chooseOption() {
	    String[] options = {"1: print paragraph", "2: insert paragraph", "3: delete paragraph", "4: replace", "5: index Words",
                "6: print formated text"};
        System.out.println("What do you want to do with your text?");

        for (int i = 0; options.length - 1 >= i; i++){
            System.out.println(options[i]);
        }

	    switch (input.intIn()) {
            case 1: output.printNumberedParagrap(text); break;
            case 2: insertParagraph(); break;
            case 3: deleteParagraph(); break;
            case 4: replace(); break;
            case 5: indexWords(); break;
            case 6: formatedText(); break;
            default: System.out.println("wrong input, try again!"); break;
	    }
    }

	private void insertParagraph() {
	    //TODO: paragraph einsetzen

    }

    private void deleteParagraph() {
	    //TODO: paragraph löschen

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
