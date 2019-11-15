import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EncryptionTest {
    private Editor editor;
    private Encryption encryptor;
    private final String text = "The quick";
    private final String encryptionError = "Encryption failed!";
    private final String decryptionError = "Decryption failed!";
    private final ArrayList<Integer> expectedEncryptionValues = new ArrayList<>(Arrays.asList(
             398, 418, 415, 361, 427, 431, 419, 413, 421, 361));
    private final String expectedDecryptionValues =
            "398, 418, 415, 361, 427, 431, 419, 413, 421, 361";

    private ArrayList<String> testParagraph = new ArrayList<>();

    @BeforeEach
    void SetUp() {
        editor = new Editor();
        encryptor = new Encryption();
        testParagraph.add(text);
        Editor.setParagraphs(testParagraph); // Fill ArrayList in editor object with above mentioned sentences
    }

    @AfterEach
    void TearDown() {
        testParagraph.clear(); // Clearing out ArrayList so it can be filled with standard values
    }

    @Test
    void testEncrypt() {
        assertEquals(encryptionError, expectedEncryptionValues, encryptor.encrypt("dog"));
    }

    @Test
    void testDecrypt() {
        assertEquals(decryptionError, text+" ", encryptor.decrypt("dog", expectedDecryptionValues));
    }
}
