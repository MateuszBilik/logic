import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Sentence {

    Logger log = Logger.getLogger(getClass());

    public String getSentence() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //TODO more line
        return reader.readLine();
    }

    public List<String> removeSpecialChars(String text) throws IOException {
        Properties prop = getProp();
        String specialChars = prop.getProperty("special").replace(" ", "");
        List<String> specialsList = Arrays.asList(specialChars.split(""));
        text = text.trim();
        text = text.replaceAll("\\s+", " ");

        for (String s : specialsList) {
            text = text.replace(s, "");
        }
        return Arrays.asList(text.split(" "));
    }

    private Properties getProp() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/config.properties"));
        return prop;
    }

    public int count(List<String> texts) {
        int count = 0;
        for (String s : texts) {
            count += s.length();
        }
        return count;
    }

    public int countSpecial(String text) throws IOException {
        Properties prop = getProp();
        String[] searchChars = prop.getProperty("logic").toLowerCase().split("");
        text = text.toLowerCase();
        String withoutSearchChars = text;
        for (String s : searchChars) {
            withoutSearchChars = withoutSearchChars.replaceAll(s, "");
        }
        return text.length() - withoutSearchChars.length();
    }



    public void printRapport () {

    }
}
