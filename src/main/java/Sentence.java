import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sentence {

    public String getSentence() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //TODO more line
        return reader.readLine();
    }

    private String removeSpecialChars(String text) {

    }
}
