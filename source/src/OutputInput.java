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

	public void setOutput(String output) {
		this.output = output;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public String getInput() {
		return input;
	}
}
