import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class EditorTest {
    Editor editor;
    private String test1 = "The quick brown fox jumps over the lazy dog";
    private String test2 = "The lazy dog jumps over the quick brown fox";
    private String test3 = "The lazy fox jumps over the quick brown dog";
    private String errorText = "Text stimmt nicht ueberein!";
    private ArrayList<String> testParagraph = new ArrayList<String>();

    @BeforeEach
    public void SetUp() {
        editor = new Editor();
        testParagraph.add(test1);
        testParagraph.add(test2);
        testParagraph.add(test3);
        editor.setParagraphs(testParagraph);
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
}
