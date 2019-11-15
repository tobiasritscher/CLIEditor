/**
 * This class calls the right method from the editor and is entry point of the program
 */
public class Logic {
    private static Editor editor;
    private static Output output;
    private static Encryption encryption;

    /**
     * Constructor of the class Logic
     */
    public Logic() {
        editor = new Editor();
        output = new Output();
        encryption = new Encryption();
    }

    public enum ChosenOption {
        STOP("0"),
        PRINT_PARAGRAPHS("1"),
        INSERT_PARAGRAPH("2"),
        DELETE_PARAGRAPH("3"),
        REPLACE_WORD("4"),
        INDEX_WORDS("5"),
        PRINT_FORMATED_TEXT("6"),
        ENCRYPT("7"),
        DECRYPT("8"),
        WRONG_INPUT("ERROR");

        private final String chosenOptionCode;

        ChosenOption(String chosenOptionCode) {
            this.chosenOptionCode = chosenOptionCode;
        }

        public static ChosenOption valueOfLabel(String label) {
            for (ChosenOption value : values()) {
                if (value.chosenOptionCode.equals(label)) {
                    return value;
                }
            }
            return WRONG_INPUT;
        }
    }

    /**
     * main method of the program, here it all begins
     *
     * @param args arguments passed to the code when executing the program
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
        final String[] options = {"\n0: Exit the programm (also prints the final text)", "1: Print paragraphs",
                "2: Insert paragraph", "3: Delete paragraph", "4: Replace a word in a paragraph",
                "5: Index of words being used", "6: Print formatted text", "7: Encrypt a text", "8: Decrypt a text"};
        // print all the options
        for (String s : options) {
            output.print(s);
        }
    }

    private boolean callEditingOption(String userInput) {
        boolean stopProgramm = false;
        ChosenOption chosenOption = ChosenOption.valueOfLabel(userInput);
        // switch case to choose the option
        assert chosenOption != null;
        switch (chosenOption) {

            case STOP:
                output.printNumberedParagraph(Editor.getParagraphs());
                stopProgramm = true;
                break;

            case PRINT_PARAGRAPHS:
                output.printNumberedParagraph(Editor.getParagraphs());
                break;

            case INSERT_PARAGRAPH:
                OutputInput chooseParagraphNumber;
                boolean wrongNumber;
                do {
                    wrongNumber = false;
                    chooseParagraphNumber = new OutputInput(
                            "Please type the position at which you would like your paragraph to be placed at [1, " + Editor.getParagraphs().size() + "]: ");
                    if (Integer.parseInt(chooseParagraphNumber.getInput()) < 1 || Integer.parseInt(chooseParagraphNumber.getInput()) > Editor.getParagraphs().size()) {
                        output.print("Wrong paragraph selected. Try again");
                        wrongNumber = true;
                    }
                } while (wrongNumber);
                OutputInput chooseNewParagraph = new OutputInput("Now type the desired paragraph: ");
                editor.insertParagraph(chooseParagraphNumber.getInput(), chooseNewParagraph.getInput());
                break;

            case DELETE_PARAGRAPH:
                OutputInput chooseParagraphToDelete;
                boolean wrongNumberedParagraph;

                do {
                    wrongNumberedParagraph = false;
                    chooseParagraphToDelete =
                            new OutputInput("Which paragraph would you like to delete? [1, " + Editor.getParagraphs().size() + "]: ");
                    int chosenParagraphDelete = Integer.parseInt(chooseParagraphToDelete.getInput());

                    if (chosenParagraphDelete < 1 || chosenParagraphDelete > Editor.getParagraphs().size()) {
                        output.print("Wrong number! Please type a correct Paragraph number!");
                        wrongNumberedParagraph = true;
                    }
                } while (wrongNumberedParagraph);

                editor.deleteParagraph(chooseParagraphToDelete.getInput());
                break;

            case REPLACE_WORD:
                OutputInput chooseParagraph;
                String chosenParagraphReplace;
                OutputInput oldWord;
                int paragraphNumberReplace;
                boolean wrongParagraphReplace;
                boolean wrongWordReplace;

                do {
                    wrongParagraphReplace = false;
                    chooseParagraph =
                            new OutputInput("In which paragraph would you like to replace a word? [1, " + Editor.getParagraphs().size() + "]: ");
                    paragraphNumberReplace = Integer.parseInt(chooseParagraph.getInput());

                    if (paragraphNumberReplace < 1 || paragraphNumberReplace > Editor.getParagraphs().size()) {
                        output.print("Wrong paragraph. PLease try again");
                        wrongParagraphReplace = true;
                    }
                } while (wrongParagraphReplace);

                chosenParagraphReplace = Editor.getParagraphs().get(paragraphNumberReplace - 1);
                do {
                    wrongWordReplace = false;
                    oldWord = new OutputInput("Which word would you like to replace?");
                    if (!chosenParagraphReplace.contains(oldWord.getInput())) {
                        output.print("Attention! Word does not exist in this paragraph!");
                        wrongWordReplace = true;
                    }
                } while (wrongWordReplace);
                OutputInput newWord = new OutputInput("Which is the new word?");
                editor.replace(paragraphNumberReplace, oldWord.getInput(), newWord.getInput());
                break;

            case INDEX_WORDS:
                editor.indexWords();
                break;

            case PRINT_FORMATED_TEXT:
                OutputInput chooseParagraphLength;
                boolean wrongInputFormatedText = true;

                do {
                    chooseParagraphLength = new OutputInput("How long should your paragraphs be?: ");
                    if (Integer.parseInt(chooseParagraphLength.getInput()) > 0)
                        wrongInputFormatedText = false;
                    else
                        output.print("Please give a number greater then zero!");
                } while (wrongInputFormatedText);

                editor.printFormattedText(chooseParagraphLength.getInput());
                break;

            case ENCRYPT:
                OutputInput encryptKey = new OutputInput("What should the key for encryption be?: ");
                encryption.encrypt(encryptKey.getInput());
                break;

            case DECRYPT:
                OutputInput decryptKey = new OutputInput("Please give the key required for decryption");
                OutputInput text = new OutputInput("Please give the encrypted text with each number separated by a coma.");
                encryption.decrypt(decryptKey.getInput(), text.getInput());
                break;

            default:
                output.print("Wrong input, try again!");
                break;
        }
        return stopProgramm;
    }
}
