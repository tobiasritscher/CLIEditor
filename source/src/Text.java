
import java.util.Arrays;
import java.util.List;

public class Text {
    private List<String> splitText;

    public Text (String input) {
        splitText = Arrays.asList(input.split("\n"));
    }

    public List<String> getArrayList() {
        return splitText;
    }

}
