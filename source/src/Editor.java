import java.util.ArrayList;
import java.util.List;

public class Editor {
    private static List<String> text;

	public Editor() {
        text = new ArrayList<>();
	}

	public static void main(String[] args){
		Output output = new Output();
        Input input = new Input();

        text = input.readInput();
        output.printNumberedParagrap(text);
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
