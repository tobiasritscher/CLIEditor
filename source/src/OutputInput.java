/**
 * Connects the the input to the correct output
 */
public class OutputInput {
    private String input;

    public OutputInput(String output) {
        Output outObject = new Output();
        Input inObject = new Input();

        outObject.print(output);
        input = inObject.stringIn();
    }

    public String getInput() {
        return input;
    }
}
