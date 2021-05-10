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

    private List<String> countSpecialsInWord(String text) throws IOException {
        String[] searchChars = arraySpecial();
        text = text.toLowerCase();
        String withoutSpecialChars = text;
        List<String> response = new ArrayList<>();
        response.add(0, "");
        for (String s : searchChars) {
            String withoutS = text;
            withoutS = withoutS.replaceAll(s, "");
            withoutSpecialChars = withoutSpecialChars.replaceAll(s, "");

            int i = 0;
            if ((i = text.length() - withoutS.length()) != 0) {
                response.set(0, response.get(0) + s);
            }
        }
        response.add(1, String.valueOf(text.length() - withoutSpecialChars.length()));
//        System.out.println(response);
        return response;
    }

    private String[] arraySpecial() {
        return prop.getProperty("logic").toLowerCase().split("");
    }

    public void printRapport() throws IOException {
        String sentence = getSentence();
        int specialCharsInSentence = countAllSpecials(sentence);
        List<String> words = removeSpecialChars(sentence);

        for (String s : words){
            List<String> wordList = countSpecialsInWord(s);
            double part = Integer.parseInt(wordList.get(1)) * 100d / specialCharsInSentence;
            part = Math.round(part);
            System.out.println(
                    "("+ Arrays.asList(wordList.get(0).split("")).toString() +
                    ", " + s.length() +
                    ") = " + part/100 +
                    " (" +  wordList.get(1) + "/" + specialCharsInSentence + ")" + "word: "+ s);
        }


    }
}
