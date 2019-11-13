import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class EditorTest {
    Editor editor;


    @BeforeEach
    public void SetUp() {
        editor = new Editor();

    }

    @Test
    void testChooseAndSetText() {
        String test1 = "The quick brown fox jumps over the lazy dog";
        String test2 = "The lazy dog jumps over the quick brown fox";

        // 1 Zeile
        ArrayList<String> testParagraph = new ArrayList<String>();
        testParagraph.add(test1);
        testParagraph.add(test2);



        editor.setParagraphs(testParagraph);
        assertEquals("Text stimmt nicht ueberein!", test1, Editor.getParagraphs().get(0));
    }


}
