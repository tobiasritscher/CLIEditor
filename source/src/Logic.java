/**
 * This class calls the right method from the editor and is the start of the
 * programm
 */
public class Logic {
	private static Editor editor;
	private static Output output;

	public Logic() {
		editor = new Editor();
		output = new Output();
	}

	public enum ChoosenOption {
	    STOP(0),
	    PRINT_PARAGRAPHS(1),
        INSERT_PARAGRAPH(2),
        DELETE_PARAGRAPH(3),
        REPLACE_WORD(4),
        INDEX_WORDS(5),
        PRINT_FORMATED_TEXT(6);

        private final int choosenOptionCode;

        ChoosenOption(int choosenOptionCode) {
            this.choosenOptionCode = choosenOptionCode;
        }

        public int getchoosenOptionCode() {
            return this.choosenOptionCode;
        }

    }

	/**
	 * main method of the programm, here it all begins
	 *
	 * @param args arguments passed to the code when exicuting the programm
	 */
	public static void main(String[] args) {
		Logic logic = new Logic();
		boolean stopProgramm;

		OutputInput chooseText = new OutputInput("Do you want to use your own text? [Y/N]: ");
		editor.chooseAndSetText(chooseText.getInput());

		do {
			logic.printOptions();

			OutputInput chooseOption = new OutputInput(
					"What do you want to do with your text? (Just write the number [0,6]): ");
			stopProgramm = logic.callEditingOption(chooseOption.getInput());
		} while (!stopProgramm);
	}

	private void printOptions() {
		String[] options = { "\n0: Exit the programm (also prints the final text)", "1: Print paragraphs",
				"2: Insert paragraph", "3: Delete paragraph", "4: Replace a word in a paragraph", "5: Index Words",
				"6: Print formated text" };
		// print all the options
		for (String s : options) {
			output.print(s);
		}
	}

	public boolean callEditingOption(String userInput) {
		boolean stopProgramm = false;
		// switch case to choose the option
		switch (userInput) {
		case "0":
			output.printNumberedParagraph(Editor.getParagraphs());
			stopProgramm = true;
			break;
		case "1":
			output.printNumberedParagraph(Editor.getParagraphs());
			break;
		case "2":
			OutputInput chooseParagraphPosition = new OutputInput(
					"Please type the position at which you would like your paragraph to be placed at: ");
			OutputInput chooseNewParagraph = new OutputInput("Now type the desired paragraph: ");
			editor.insertParagraph(chooseParagraphPosition.getInput(), chooseNewParagraph.getInput());
			break;
		case "3":
			OutputInput chooseParagraphToDelete = new OutputInput("Which paragraph would you like to delete?: ");
			editor.deleteParagraph(chooseParagraphToDelete.getInput());
			break;
		case "4":
			OutputInput chooseParagraph;
			String chosenParagraph;
			OutputInput oldWord;
			boolean wrongParagraph;
			boolean wrongWord;
			do {
				wrongParagraph = false;
				chooseParagraph = new OutputInput("In which paragraph would you like to replace a word?");
				if (Integer.parseInt(chooseParagraph.getInput()) < 1 || Integer.parseInt(chooseParagraph.getInput()) > Editor.paragraphs.size()) {
					System.out.println("Wrong paragraph. PLease try again");
					wrongParagraph = true;
				}
			} while(wrongParagraph);
			chosenParagraph = Editor.paragraphs.get(Integer.parseInt(chooseParagraph.getInput()) - 1);
			do {
				wrongWord = false;
				oldWord = new OutputInput("Whhich word would you like to replace?");
				if(!chosenParagraph.contains(oldWord.getInput())){
					System.out.println("Attention! Word does not exist in this paragraph!");
					wrongWord = true;}
				} while(wrongWord);
				OutputInput newWord = new OutputInput("Which is the new word?");
				editor.replace(chooseParagraph, chosenParagraph, oldWord.getInput(), newWord.getInput());
			break;
		case "5":
			editor.indexWords();
			break;
		case "6":
			OutputInput chooseParagraphLength = new OutputInput("How long should your paragraphs be?: ");
			editor.printFormatedText(chooseParagraphLength.getInput());
			break;
		default:
			output.print("Wrong input, try again!");
			break;
		}
		return stopProgramm;
	}
}
