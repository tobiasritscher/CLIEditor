import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing class for Editor.java.
 *
 * @author Oliver
 * @version 1.0
 */

public class EditorTest {
    private Editor editor;
    private final String test1 = "The quick brown fox jumps over the lazy dog";
    private final String test2 = "The lazy dog jumps over the quick brown fox";
    private final String test3 = "The lazy fox jumps over the quick brown dog";
    private final String errorText = "Text doesn't match expected result!";
    private ArrayList<String> testParagraph = new ArrayList<>();

    @BeforeEach
    public void SetUp() {
        editor = new Editor();
        testParagraph.add(test1);
        testParagraph.add(test2);
        testParagraph.add(test3);
        Editor.setParagraphs(testParagraph); // Fill ArrayList in editor object with above mentioned sentences
    }

    @AfterEach
    public void TearDown() {
        testParagraph.clear(); // Clearing out ArrayList so it can be filled with standard values
    }

    /*
    Checks whether paragraphs in Editor class are filled properly and in correct order.
     */
    @Test
    void testChooseAndSetText() {
        assertEquals(errorText, test1, Editor.getParagraphs().get(0));
        assertEquals(errorText, test2, Editor.getParagraphs().get(1));
        assertEquals(errorText, test3, Editor.getParagraphs().get(2));
    }

    /*
    Checks whether Paragraphs are correctly inserted and following paragraphs are correctly moving down
     */
    @Test
    void testInsertParagraph() {
        String paragraphToInsert = "New Paragraph";
        editor.insertParagraph("2", paragraphToInsert);
        assertEquals(errorText, paragraphToInsert, Editor.getParagraphs().get(1));
        assertEquals(errorText, test2, Editor.getParagraphs().get(2));
    }

    /*
    Checks whether Paragraphs are correctly deleted and following paragraphs are correctly moving up
     */
    @Test
    void testDeleteParagraph() {
        editor.deleteParagraph("1");
        assertEquals(errorText, test2, Editor.getParagraphs().get(0));
    }

    /*
    Checks whether words are correctly replaced within the correct paragraph number.
     */
    @Test
    void testReplace() {
        editor.replace(1, "fox", "wolf");
        assertEquals(errorText, "The quick brown wolf jumps over the lazy dog", Editor.getParagraphs().get(0));
    }

    /*
    Checks if the indexWords List are filled with the correct values (amount and corresponding paragraph numbers)
     */
    @Test
    void testIndexWords() {
        editor.indexWords();
        List<Integer> testList = new ArrayList<>(Arrays.asList(3, 1, 2, 3));
        assertEquals(errorText, testList, editor.getWordIndex().get("fox"));
    }

    /*
    Checks whether all special characters are correctly deleted and uppercase letters are converted to lowercase.
     */
    @Test
    void testCleanInput() {
        assertEquals(errorText, "thequickbrown123", editor.cleanInput("#T.h,e& 'Q?u!i_c-k B*r+ow(n)1\"2%3§"));
    }

    /*
    Checks if the paragraphs are broken up at the defined character count.
     */
    @Test
    void testFormattedText() {
        // standard test
        String expectedString = "The quick\nbrown fox\njumps over\nthe lazy\ndog "
                + "The\nlazy dog\njumps over\nthe quick\nbrown fox\n"
                + "The lazy\nfox jumps\nover the\nquick\nbrown dog ";
        assertEquals(errorText, expectedString, editor.printFormattedText("10"));

        // mid-word line break test
        expectedString = "The\nqui\n-ck\nbro\n-wn\nfox\njum\n-ps\nove\n-r\nthe\nlaz\n-y\ndog\n"
                + "The\nlaz\n-y\ndog\njum\n-ps\nove\n-r\nthe\nqui\n-ck\nbro\n-wn\nfox\n"
                + "The\nlaz\n-y\nfox\njum\n-ps\nove\n-r\nthe\nqui\n-ck\nbro\n-wn\ndog ";
        assertEquals(errorText, expectedString, editor.printFormattedText("3"));
    }

    /*
    Checks whether the paragraph list is correctly converted to a single string.
     */
    @Test
    void testListToString() {
        assertEquals(errorText, test1 + " " + test2 + " " + test3 + " ", editor.listToString(testParagraph));
    }
}