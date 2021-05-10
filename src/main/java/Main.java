import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
       String s = new Sentence().getSentence();
       List<String> s2 = new Sentence().removeSpecialChars(s);
    }
}