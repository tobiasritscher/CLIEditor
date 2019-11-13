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
        String test3 = "The lazy fox jumps over the quick brown dog";


        ArrayList<String> testParagraph = new ArrayList<String>();
        testParagraph.add(test1);
        testParagraph.add(test2);
        testParagraph.add(test3);




        editor.setParagraphs(testParagraph);
        assertEquals("Text stimmt nicht ueberein!", test1, Editor.getParagraphs().get(0));
        assertEquals("Text stimmt nicht ueberein!", test2, Editor.getParagraphs().get(1));
        assertEquals("Text stimmt nicht ueberein!", test3, Editor.getParagraphs().get(2));

    }


}
