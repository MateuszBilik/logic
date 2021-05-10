import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Sentence sentence = new Sentence();
        String s = sentence.getSentence();
        List<String> s2 = sentence.removeSpecialChars(s);
        int i = sentence.count(s2);
        int j = sentence.countSpecial(s);
        System.out.println(s + s2 + i + j);

    }
}