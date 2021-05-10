import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sentence {

    Logger log = Logger.getLogger(getClass());
    Properties prop = getProp();

    public String getSentence() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //TODO more line
        try {
            return reader.readLine();
        } catch (IOException e) {
            log.error("Something wrong with command: " + e.getMessage());
            return "";
        }
    }

    public List<String> removeSpecialChars(String text) throws IOException {
        String specialChars = prop.getProperty("special").replace(" ", "");
        List<String> specialsList = Arrays.asList(specialChars.split(""));

        for (String s : specialsList) {
            text = text.replace(s, "");
        }
        text = text.trim();
        text = text.replaceAll("\\s+", " ");

        return Arrays.asList(text.split(" "));
    }

    private Properties getProp() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            log.error("Can't find properties file: " + e.getMessage());
        }
        return prop;
    }

    public int countChars(List<String> texts) {
        int count = 0;
        for (String s : texts) {
            count += s.length();
        }
        return count;
    }

    public int countAllSpecials(String text) throws IOException {
        String[] searchChars = arraySpecial();
        text = text.toLowerCase();
        String withoutSearchChars = text;
        for (String s : searchChars) {
            withoutSearchChars = withoutSearchChars.replaceAll(s, "");
        }
        return text.length() - withoutSearchChars.length();
    }

    private OneWord countSpecialsInWord(String text) throws IOException {
        String[] searchChars = arraySpecial();
        text = text.toLowerCase();
        String withoutSpecialChars = text;
        OneWord word = new OneWord();
        for (String s : searchChars) {
            word.setLengthOfWord(text.length());
            String withoutS = text;
            withoutS = withoutS.replaceAll(s, "");
            withoutSpecialChars = withoutSpecialChars.replaceAll(s, "");
            int i = 0;
            if ((i = text.length() - withoutS.length()) != 0) {
                word.setSpecialChars(word.getSpecialChars() + s);
            }
        }
        word.setNumberOfSpecial(text.length() - withoutSpecialChars.length());
        return word;
    }

    private String[] arraySpecial() {
        return prop.getProperty("logic").toLowerCase().split("");
    }

    public String printRapport() throws IOException {
        String sentence = getSentence();
        List<String> words = removeSpecialChars(sentence);
        int searchCharsInSentence = countAllSpecials(sentence);
        int allCharsInSentence = countChars(words);
        List<OneWord> response = new ArrayList<>();

        double part = searchCharsInSentence * 100d / allCharsInSentence;
        part = Math.round(part);

        for (String s : words) {
            OneWord word = countSpecialsInWord(s);
            word.setNumberOfAllSpecial(searchCharsInSentence);
            word.setNumberOfAllChars(allCharsInSentence);
            response.add(word);
        }
        response.sort(Comparator.comparing(OneWord::getLengthOfWord));
        StringBuilder sb = new StringBuilder().append("\n");
        for (OneWord o : response) {
            sb.append(o + "\n");
        }
        sb.append(
                "TOTAL Frequency: " + part / 100 + " (" + searchCharsInSentence + "/" + allCharsInSentence + ")");
        log.info(sb.toString());
        return sb.toString();
    }
}
