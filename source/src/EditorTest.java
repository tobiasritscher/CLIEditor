import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EditorTest {
    private Editor editor;
    private final String test1 = "The quick brown fox jumps over the lazy dog";
    private final String test2 = "The lazy dog jumps over the quick brown fox";
    private final String test3 = "The lazy fox jumps over the quick brown dog";
    private final String errorText = "Text stimmt nicht ueberein!";
    private ArrayList<String> testParagraph = new ArrayList<>();

    @BeforeEach
    public void SetUp() {
        editor = new Editor();
        testParagraph.add(test1);
        testParagraph.add(test2);
        testParagraph.add(test3);
        Editor.setParagraphs(testParagraph); //ArrayList in Editor einspeisen
    }

    @AfterEach
    public void TearDown() {
        testParagraph.clear();
    }

    @Test
    void testChooseAndSetText() {
        assertEquals(errorText, test1, Editor.getParagraphs().get(0));
        assertEquals(errorText, test2, Editor.getParagraphs().get(1));
        assertEquals(errorText, test3, Editor.getParagraphs().get(2));
    }

    @Test
    void testInsertParagraph() {
        String paragraphToInsert = "New Paragraph";
        editor.insertParagraph("2", paragraphToInsert);
        assertEquals(errorText, paragraphToInsert, Editor.getParagraphs().get(1));
        assertEquals(errorText, test2, Editor.getParagraphs().get(2));
    }

    @Test
    void testDeleteParagraph() {
        editor.deleteParagraph("1");
        assertEquals(errorText, test2, Editor.getParagraphs().get(0));
    }

    @Test
    void testReplace() {
        // TODO: Methode schreiben
    }

    @Test
    void testindexWords() {
        // TODO: Methode schreiben
    }

    @Test
    void testCleanInput() {
        assertEquals(errorText, "thequickbrown123", editor.cleanInput("#t.h,e& 'q?u!i_c-k b*r+ow(n)1\"2%3§"));
    }

    @Test
    void testFormattedText() {
        String expectedString = "The quick\nbrown fox\njumps over\nthe lazy\ndog "
                + "The\nlazy dog\njumps over\nthe quick\nbrown fox\n"
                + "The lazy\nfox jumps\nover the\nquick\nbrown dog ";
        assertEquals(errorText, expectedString, editor.printFormattedText("10"));
    }

    @Test
    void testListToString() {
        assertEquals(errorText, test1 + " " + test2 + " " + test3 + " ", editor.listToString(testParagraph));
    }
}