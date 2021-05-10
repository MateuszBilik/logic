import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Sentence {

    public String getSentence() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //TODO more line
        return reader.readLine();
    }

    public List<String> removeSpecialChars(String text) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/config.properties"));
        String specialChars = prop.getProperty("special").replace(" ", "");
        List<String> specialsList = Arrays.asList(specialChars.split(""));
        text.trim();
        text.replaceAll("\\s+", " ");

        for (String s : specialsList) {
            text = text.replace(s, "");
        }
        return Arrays.asList(text.split(" "));
    }
}
