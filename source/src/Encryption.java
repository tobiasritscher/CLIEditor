import java.util.ArrayList;
import java.util.Arrays;

public class Encryption {
    private Editor editor;

    public Encryption(){
        editor = new Editor();
    }

    private int key(String key) {
        int total = 0;
        int[] letters = new int[key.length()];

        for (int i = 0; i < key.length(); ++i) {
            letters[i] = key.charAt(i);
        }

        for (int n : letters) {
            total += n;
        }

        return total;
    }

    /**
     * Uses our very own encryption algorythm to encrypt your text into an Array of ints
     *
     * @param inputKey the key which is used to encryption and later decryption
     */
    public void encrypt(String inputKey) {
        ArrayList<Integer> total = new ArrayList<>();

        for (int i = 0; i < Editor.getParagraphs().size(); ++i) {
            total.addAll(paragraphtoASCII(i));
        }

        for (int i = 0; i < total.size(); ++i) {
            total.set(i, total.get(i) + key(inputKey));
        }

        System.out.println(total);
    }

    private ArrayList<Integer> paragraphtoASCII(int i) {
        ArrayList<Integer> ascii = new ArrayList<>();
        String[] words;

        words = Editor.getParagraphs().get(i).split(" ");

        for (int k = 0; k < words.length; ++k) {
            words[k] = words[k].concat("/");
        }

        for (String word : words) {
            for (int n = 0; n < word.length(); ++n) {
                ascii.add((int) word.charAt(n));
            }
        }
        return ascii;
    }

    /**
     * Used to decrypt your message
     *
     * @param key the String used to decrypt the message (the same key you used to encrypt your message)
     * @param text the encrypted message coma separated
     */
    public void decrypt(String key, String text) {
        String[] numbersAsString;
        StringBuilder words = new StringBuilder();

        numbersAsString = text.split(", ");
        int[] numbers = new int[numbersAsString.length];

        for (int i = 0; i < numbersAsString.length; ++i) {
            numbers[i] = Integer.parseInt(numbersAsString[i]);
        }

        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = numbers[i] - key(key);
        }

        char[] chars = new char[numbers.length];
        for (int i = 0; i < numbers.length; ++i) {
            chars[i] = (char) numbers[i];
        }

        for (char aChar : chars) {
            words.append(aChar);
        }

        String[] sentences = words.toString().split("/");
        System.out.println(editor.listToString(Arrays.asList(sentences)));
    }


}
