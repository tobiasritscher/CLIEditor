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
		// TODO: keyword suchen und ersetzen

	}

	/**
	 * prints an index of all words with the number of occurence and where to find
	 * them
	 */
	public void indexWords() {
		output.print("Total number of paragraphs: " + paragraphs.size());
		output.print("*******************************");

		for (int i = 0; i < paragraphs.size(); i++) {
			String[] words = paragraphs.get(i).split(" ");

			for (String s : words) {
				String word = s.replaceAll("[,.]", "");
				word = word.toLowerCase();

				if (wordIndex.get(word) == null) {
					wordIndex.put(word, new ArrayList<>());

					/*
					 * Das erste Element des Arrays zählt die Häufigkeit des Wortes. Hier wird es
					 * initialisiert.
					 */
					wordIndex.get(word).add(1);

					// Das zweite Element des Arrays gibt an, in welchem Paragraph das Wort zum
					// ersten Mal auftritt
					wordIndex.get(word).add(i + 1);

				} else {
					// wordIndex.put(word,wordFrequency + 1);
					wordIndex.get(word).set(0, wordIndex.get(word).get(0) + 1);

					// Fügt hinzu, in welchem Paragraphen das Wort noch auftritt
					wordIndex.get(word).add(i + 1);
				}
			}
		}
		printIndex();
	}

	private void indexInParagraph() {
		// TODO: Speichert in welchem Paragraph sich das gesuchte Wort befindet
	}

	private void printIndex() {
		for (Map.Entry<String, List<Integer>> word : wordIndex.entrySet()) {
			String key = word.getKey();
			List<Integer> values = word.getValue();

			// Nur ausgeben, wenn das Wort häufiger als ein mal vorkommt
			if (values.get(0) > 1) {
				System.out.print("'" + key + "'");
				System.out.print(" exists " + values.get(0) + " times in paragraphs [");
				for (int i = 1; i < values.size() - ARRAY_OFFSET; i++) {
					System.out.print(values.get(i) + ", ");
				}
				// Print last occurrence of word
				System.out.println(values.get(values.size() - ARRAY_OFFSET) + "]");
			}
		}
	}

	/**
	 * This method formats the text with a chosen length of the paragraph
	 *
	 * @param lengthInput int > 0 for the maxLength of the paragraph
	 */
	public void formatedText(String lengthInput) {
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
				paragraphLength += s.length();
				// if word doesn't fit into line -> put it on next line
			} else if (paragraphLength + s.length() > maxLength && s.length() <= maxLength) {
				text.append("\n");
				text.append(s);
				text.append(" ");
				paragraphLength = s.length();
				// if word is longer then maxlength -> put it on several lines parted by "-"
			} else if (s.length() > maxLength) {
				int paragraphLengthCache = paragraphLength;
				int i = 0;
				int startOfSubstring = 0;
				int lengthOfWord = s.length();
				do {
					int endOfSubstring = startOfSubstring + maxLength - paragraphLengthCache;
					;

					text.append(s, startOfSubstring, endOfSubstring);

					text.append("\n-");
					i++;
					paragraphLengthCache = 0;
					lengthOfWord -= (endOfSubstring - startOfSubstring);
					startOfSubstring = endOfSubstring;
				} while (lengthOfWord > maxLength);

				text.append(s.substring(i * maxLength - paragraphLength));
				text.append(" ");

				paragraphLength = s.length() + paragraphLength - i * maxLength;
			}
		}
		output.print(text.toString());
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
