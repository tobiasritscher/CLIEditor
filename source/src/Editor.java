import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;

/**
 * This Class handles all the editing of the text
 */
public class Editor {
    private static List<String> paragraphs;
    private static Input input;
    private static Output output;
    private static LoremIpsum loremIpsum;
    private static Map<String, List<Integer>> wordIndex = new TreeMap<>();
    private final static int ARRAY_OFFSET = 1;

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
     * Setter method for Paragraphs List
     *
     * @param paragraphs List<String>
     */
    public static void setParagraphs(List<String> paragraphs) {
        Editor.paragraphs = paragraphs;
    }

    /**
     * Getter method for the paragraph List
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
    public List<String> chooseAndSetText(String userInput) {
        if (userInput.equalsIgnoreCase("Y")) {
            output.print("Please insert your text. At the end of your text switch to a new line and write 'END':");
            paragraphs = input.readInput();
        } else {
            paragraphs = loremIpsum.getTextArray();
        }
        return paragraphs;
    }

    /**
     * Inserts a paragraph at a chosen position
     *
     * @param positionString position of the new paragraph as String
     * @param newParagraph   paragraph to set as String
     */
    public void insertParagraph(String positionString, String newParagraph) {
        int position = Integer.parseInt(positionString) - ARRAY_OFFSET;
        paragraphs.add(position, newParagraph);
        output.printNumberedParagraph(Editor.getParagraphs());
    }

    /**
     * Deletes a paragraph at the chosen position
     *
     * @param positionString position of the paragraph to delete as a string
     */
    public void deleteParagraph(String positionString) {
        int position = Integer.parseInt(positionString) - ARRAY_OFFSET;
        paragraphs.remove(position);
        output.printNumberedParagraph(Editor.getParagraphs());
    }

    void replace(int whichParagraph, String oldWord, String newWord) {
        //Replaces a choosen String with a new one
        String paragraph = paragraphs.get(whichParagraph - ARRAY_OFFSET).replaceFirst(oldWord, newWord);
        paragraphs.set(whichParagraph - ARRAY_OFFSET, paragraph);
        output.printNumberedParagraph(Editor.getParagraphs());
    }

    public void indexWords() {
        // prints an index of all words with the number of occurrence and where to find them
        output.print("Total number of paragraphs: " + paragraphs.size());
        output.print("*******************************");
        indexInParagraph();
        printIndex();
    }

    // This method saves in which paragraph a given word occurs
    private void indexInParagraph() {
        for (int i = 0; i < paragraphs.size(); i++) {
            String[] words = paragraphs.get(i).split(" ");

            for (String word : words) {
                // Removes special characters and sets the word to lowercase
                String cleanedWord = cleanInput(word);

                if (wordIndex.get(cleanedWord) == null) {
                    wordIndex.put(cleanedWord, new ArrayList<>());
                    wordIndex.get(cleanedWord).add(1);
                    wordIndex.get(cleanedWord).add(i + 1);

                } else { // Operations to perform if the word has already appeared once
                    wordIndex.get(cleanedWord).set(0, wordIndex.get(cleanedWord).get(0) + 1);
                    wordIndex.get(cleanedWord).add(i + 1);
                }
            }
        }
    }

    static Map<String, List<Integer>> getWordIndex() {
        return wordIndex;
    }

    // This method removes special characters from its input and sets it to lower-case
    String cleanInput(String input) {
        // Remove special characters attached to the word
        String word = input.replaceAll("[^a-zA-Z0-9]+", "");
        word = word.toLowerCase();

        return word;
    }

    private void printIndex() {
        boolean wordOccursMoreThanOnce = false;

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

                output.print(values.get(values.size() - ARRAY_OFFSET) + "]");

                wordOccursMoreThanOnce = true;
            }
        }

        if (!wordOccursMoreThanOnce) {
            output.print("Warning: No word in the given text occurs more than once");
        }
    }

    /**
     * This method formats the whole text with a chosen length. Line breaks are inserted where
     * the row length is exceeded.
     *
     * @param lengthInput int > 0 for the maximum length of the paragraph
     */
    public String printFormattedText(String lengthInput) {
        final int COUNT_SPACES = 1;
        int maxLength = Integer.parseInt(lengthInput);
        List<String> wordList = words(paragraphs);
        StringBuilder text = new StringBuilder();
        int paragraphLength = 0;

        for (String s : wordList) {

            if (paragraphLength >= maxLength) {
                text.append("\n");
                paragraphLength = 0;
            }
            // if word fits into line -> put it there
            if (paragraphLength + s.length() <= maxLength) {
                text.append(s);
                text.append(" ");
                paragraphLength += s.length() + COUNT_SPACES;
                // if word doesn't fit into line -> put it on next line
            } else if (paragraphLength + s.length() > maxLength && s.length() <= maxLength) {
                text.append("\n");
                text.append(s);
                text.append(" ");
                paragraphLength = s.length() + COUNT_SPACES;
                // if word is longer then maxlength -> put it on several lines parted by "-"
            } else if (s.length() > maxLength) {
                int paragraphLengthCache = paragraphLength;
                int counter = 0;
                int startOfSubstring = 0;
                int lengthOfWord = s.length();
                do {
                    int endOfSubstring = startOfSubstring + maxLength - paragraphLengthCache;
                    text.append(s, startOfSubstring, endOfSubstring);
                    text.append("\n-");

                    counter++;
                    paragraphLengthCache = 0;
                    lengthOfWord -= (endOfSubstring - startOfSubstring);
                    startOfSubstring = endOfSubstring;
                } while (lengthOfWord > maxLength);

                text.append(s.substring(counter * maxLength - paragraphLength));
                text.append(" ");

                paragraphLength = s.length() + COUNT_SPACES + paragraphLength - counter * maxLength;
            }
        }
        output.print(text.toString().replaceAll(" \n", "\n"));
        return text.toString().replaceAll(" \n", "\n");
    }

    private List<String> words(List<String> text) {
        String textString = listToString(text);

        return Arrays.asList(textString.split(" "));
    }

    String listToString(List<String> text) {
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

    private int key(String key){
        int total = 0;
        int [] letters = new int[key.length()];
        for(int i = 0; i < key.length(); ++i){
            letters[i] = (int) key.charAt(i);
        }
        for(int n : letters){
            total += n;
        }
        return total;
    }

    //Tobi tuen die Methode i de switch cases zum teste
    public void encrypt(){
        OutputInput key = new OutputInput("What should the key for encryption be?");
        ArrayList<Integer> total = new ArrayList<>();
        for(int i = 0; i < paragraphs.size(); ++i){
            total.addAll(ParagraphtoASCII(i));
        }
        for(int i = 0; i < total.size(); ++i){
            total.set(i, total.get(i) + key(key.getInput()));
        }
        System.out.println(total);
    }

    private ArrayList<Integer> ParagraphtoASCII(int i){
        ArrayList<Integer>  ascii = new ArrayList<>();
        String[] words;
        words = paragraphs.get(i).split(" ");
        for(int k = 0; k < words.length; ++k){
            words[k] = words[k].concat("/");
        }
        for(int j = 0; j < words.length; ++j){
            for(int n = 0; n < words[j].length(); ++n){
               ascii.add((int) words[j].charAt(n));
            }
        }
        return ascii;
    }
    // Die da bitte au ine tue
    public void decrypt (){
        OutputInput key = new OutputInput("Please give the key required for decryption");
        OutputInput text = new OutputInput("Please give the encrypted text with each number separated by a coma.");
        String[] NumbersasString = new String[text.getInput().length()];
        NumbersasString = text.getInput().split(",");
        int[] numbers = new int[NumbersasString.length];
        for(int i = 0; i < NumbersasString.length; ++i){
            numbers[i] = Integer.parseInt(NumbersasString[i]);
        }
        for(int i = 0; i < numbers.length; ++i){
            numbers[i] = numbers[i] - key(key.getInput());
        }
        char[] chars = new char[numbers.length];
        for(int i = 0; i < numbers.length; ++i){
            chars[i] = (char) numbers[i];
        }
       String words = "";
        for(int i = 0; i < chars.length; ++i){
            words += chars[i];
        }
        String[] sentences = words.split("/");
        System.out.println(sentences);
    }

}