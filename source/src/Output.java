
import java.util.List;

public class Output {

    public Output(){

    }

    public void printNumberedParagrap(List<String> array){
        for(int i = 0; i < array.size(); i++) {
            System.out.println((i + 1) + ": " + array.get(i));
        }
    }

    public void print(String string){
        System.out.println(string);
    }
}
