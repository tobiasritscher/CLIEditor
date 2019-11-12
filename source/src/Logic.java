/**
 * This class calls the right method from the editor and is the start of the program
 */
public class Logic {
    private static Editor editor;
    private static Output output;

    /**
     * Constructor of the Logic class
     */
    public Logic() {
        editor = new Editor();
        output = new Output();
    }

    /**
     * main method of the program, here it all begins
     * @param args arguments passed to the code when exicuting the program
     */
    public static void main(String[] args){
        Logic logic = new Logic();
        boolean stopProgramm;

        OutputInput chooseText = new OutputInput("Do you want to use your own text? [Y/N]: ");
        editor.chooseAndSetText(chooseText.getInput());

        do {
            logic.printOptions();

            OutputInput chooseOption = new OutputInput("What do you want to do with your text? (Just write the number [0,6]): ");
            stopProgramm = logic.callEditingOption(chooseOption.getInput());
        } while (!stopProgramm);
    }

    private void printOptions() {
        String[] options = {"\n0: Exit the programm (also prints the final text)", "1: Print paragraphs", "2: Insert paragraph",
                "3: Delete paragraph", "4: Replace a paragraph", "5: Index Words", "6: Print formated text"};
        //print all the options
        for (String s : options){
            output.print(s);
        }
    }

    private boolean callEditingOption(String userInput) {
        boolean stopProgramm = false;
        //switch case to choose the option
        switch (userInput) {
            case "0": output.printNumberedParagrap(Editor.getParagraphs()); stopProgramm = true; break;
            case "1": output.printNumberedParagrap(Editor.getParagraphs()); break;
            case "2":
                OutputInput chooseParagraph = new OutputInput("Please type the position at which you would like your paragraph to be placed at: ");
                OutputInput chooseNewParagraph = new OutputInput("Now type the desired paragraph: ");
                editor.insertParagraph(chooseParagraph.getInput(), chooseNewParagraph.getInput()); break;
            case "3":
                OutputInput chooseParagraphToDelete = new OutputInput("Which paragraph would you like to delete?: ");
                editor.deleteParagraph(chooseParagraphToDelete.getInput()); break;
            case "4": editor.replace(); break;
            case "5": editor.indexWords(); break;
            case "6": editor.formatedText(); break;
            default: output.print("Wrong input, try again!"); break;
        }
        return stopProgramm;
    }
}
