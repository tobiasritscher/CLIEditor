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
        // 1 Zeile
        ArrayList<String> testParagraph = new ArrayList<String>();
        testParagraph.add("Lorem ipsum dolor sit amet");

        editor.setParagraphs(testParagraph);
        assertEquals("Text stimmt nicht ueberein", "Lorem ipsum dolor sit amet", Editor.getParagraphs().get(0));
    }

}
