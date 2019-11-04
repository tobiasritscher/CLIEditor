
public class Editor {

	public Editor() {
	}

	public static void main(String[] args){
		//MARK: only for testing
		String randomString = "Das Projekt isch voll geil\nIch freu mich scho uf Siedler\nWer das liest ist doof!";
		Text textInput = new Text(randomString);
		Output output = new Output();
		output.printNumberedParagrap(textInput.getArrayList());

		//TODO: Real Code & Class Text erstellen
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
