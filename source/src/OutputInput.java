/**
 * connects the the input to the right output
 */
public class OutputInput {
    private String output;
    private String input;

    public OutputInput(String output) {
        Output outObject = new Output();
        Input inObject = new Input();
        this.output = output;

        outObject.print(output);
        input = inObject.stringIn();
    }

    public String getInput() {
        return input;
    }
}
