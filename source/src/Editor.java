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
        OutputInput chooseParagraph = new OutputInput("In which paragraph would you like to replace a word?");
        OutputInput oldWord = new OutputInput("Which word would you like to replace?");
        OutputInput newWord = new OutputInput("Which word would you like to use instead?");
        String chosenParagraph = paragraphs.get(Integer.parseInt(chooseParagraph.getInput())-1);
        chosenParagraph = chosenParagraph.replaceFirst(oldWord.getInput(), newWord.getInput());
        paragraphs.set(Integer.parseInt(chooseParagraph.getInput())-1, chosenParagraph);
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

    /*
    This method saves in which paragraph a given word occurs
     */
    private void indexInParagraph() {
        for (int i = 0; i < paragraphs.size(); i++) {
            String[] words = paragraphs.get(i).split(" ");

            for (String word : words) {
                //Removing special characters and setting the word to lowercase
                String cleanedWord = cleanInput(word);

                if (wordIndex.get(cleanedWord) == null) {
                    wordIndex.put(cleanedWord, new ArrayList<>());

    				/*
    				Das erste Element des Arrays zählt die Häufigkeit des Wortes.
    				Hier wird es initialisiert.
    				*/
                    wordIndex.get(cleanedWord).add(1);

                    // Das zweite Element des Arrays gibt an, in welchem Paragraph das Wort zum ersten Mal auftritt
                    wordIndex.get(cleanedWord).add(i + 1);

                } else {
                    //wordIndex.put(word,wordFrequency + 1);
                    wordIndex.get(cleanedWord).set(0, wordIndex.get(cleanedWord).get(0) + 1);

                    //Fügt hinzu, in welchem Paragraphen das Wort noch auftritt
                    wordIndex.get(cleanedWord).add(i + 1);
                }
            }
        }
    }

    public String cleanInput(String input) {

        String word = input.replaceAll("[^a-zA-Z0-9]+", "");
        word = word.toLowerCase();
        return word;
    }

    private void printIndex() {
        int wordOccuringMoreThanOnce = 0;
        for (Map.Entry<String, List<Integer>> word : wordIndex.entrySet()) {
            String key = word.getKey();
            List<Integer> values = word.getValue();

            //Print only if the word occurs more than once
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

        //Print error message if no word occurs more than once
        if (wordOccuringMoreThanOnce == 0) {
            System.out.println("Warning: No word in the given text occurs more than once");
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
 