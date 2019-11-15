import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing class for Encryption.java.
 *
 * @author corrooli
 * @version 1.0
 */
class EncryptionTest {
    private Encryption encryptor;
    private final String text = "The quick";
    private final ArrayList<Integer> expectedEncryptionValues = new ArrayList<>(Arrays.asList(
            398, 418, 415, 361, 427, 431, 419, 413, 421, 361));

    private ArrayList<String> testParagraph = new ArrayList<>();

    @BeforeEach
    void SetUp() {
        encryptor = new Encryption();
        testParagraph.add(text);
        Editor.setParagraphs(testParagraph); // Fill ArrayList in editor object with above mentioned sentences
    }

    @AfterEach
    void TearDown() {
        testParagraph.clear(); // Clearing out ArrayList so it can be filled with standard values
    }

    /*
    Checks if the string within the text variable is encrypted properly.
     */
    @Test
    void testEncrypt() {
        String encryptionError = "Encryption failed!";
        assertEquals(encryptionError, expectedEncryptionValues, encryptor.encrypt("dog"));
    }

    /*
        Checks if the string within the text variable is decrypted properly.
    */
    @Test
    void testDecrypt() {
        String expectedDecryptionValues = "398, 418, 415, 361, 427, 431, 419, 413, 421, 361";
        String decryptionError = "Decryption failed!";
        assertEquals(decryptionError, text + " ", encryptor.decrypt("dog", expectedDecryptionValues));
    }
}
