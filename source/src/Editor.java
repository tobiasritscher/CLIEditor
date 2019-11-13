import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;

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
     * geter for the paragraph List
     *
     * @return all paragraphs as an list
     */
    public static List<String> getParagraphs() {
        return paragraphs;
    }

    /**
     * let you set a text you want to edit
     *
     * @param userInput String to decide wich text to use
     */
    public void chooseAndSetText(String userInput) {
        if (userInput.equalsIgnoreCase("Y")) {
            output.print("Please insert your text. At the end of your text switch to a new line and write 'END':");
            paragraphs = input.readInput();
        } else {
            paragraphs = loremIpsum.getTextArray();
        }
    }

    /**
     * inserts a choosen paragraph at a choosen position
     *
     * @param positionString position of the new paragrpah as String
     * @param newParagraph   paragraph to set as String
     */
    public void insertParagraph(String positionString, String newParagraph) {
        int position = Integer.parseInt(positionString) - ARRAY_OFFSET;
        paragraphs.add(position, newParagraph);
    }

    /**
     * delets a pragraph at the choosen position
     *
     * @param positionString position of the paragraph to delete as String
     */
    public void deleteParagraph(String positionString) {
        int position = Integer.parseInt(positionString) - ARRAY_OFFSET;
        paragraphs.remove(position);
    }

    /**
     * replaces a choosen String with a new one
     */
    public void replace() {
        //TODO: keyword suchen und ersetzen

    }

    /**
     * prints an index of all words with the number of occurence and where to find them
     */
    public void indexWords() {
        System.out.println("Total number of paragraphs: " + paragraphs.size());
        System.out.println("*******************************");
        indexInParagraph();
        printIndex();
    }
    /******
    * This method saves in which paragraph(s) the different words occur
    */
     private void indexInParagraph() {

        for (int i = 0; i < paragraphs.size(); i++) {
            String[] words = paragraphs.get(i).split(" ");

            for (String s : words) {
                String word = s.replaceAll("[^a-zA-Z0-9]+","");
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
    }

    //This function prints the index onto the command-line
    private void printIndex() {
        for (Map.Entry<String, List<Integer>> word : wordIndex.entrySet()) {
            String key = word.getKey();
            List<Integer> values = word.getValue();

            // Print only if the word exists more than once
            if (values.get(0) > 1) {
                System.out.print("'" + key + "'");
                System.out.print(" exists " + values.get(0) + " times in paragraphs [");
                for (int i = 1; i < values.size() - ARRAY_OFFSET; i++) {
                    System.out.print(values.get(i) + ", ");
                }
                //Print last occurrence of word
                System.out.println(values.get(values.size() - ARRAY_OFFSET) + "]");
            }
        }
    }

    /**
     * TODO: Formatierte Ausgabe von Text mit einer einstellbaren maximalen Spaltenbreite Zeichen,
     * Zeilenumbruch jeweils auf dem letzten Lehrzeichen
     * (bei zu langen Wörtern an beliebiger Stelle innerhalb des Wortes)
     */
    public void formatedText(String lengthInput) {
        int paragraphLength = Integer.parseInt(lengthInput);
        List<String> wordList = words(paragraphs);


    }

    private List<String> words(List<String> text) {
        String textString = listToString(text);
        return Arrays.asList(textString.split(" "));
    }

    private String listToString(List<String> text) {
        StringBuilder wordsBuilder = new StringBuilder();

        for (String value : text) {
            String[] fulltext = value.split(" ");
            for (String s : fulltext) {
                wordsBuilder.append(s);
                wordsBuilder.append(" ");
            }
        }
        return wordsBuilder.toString();
    }
}
 