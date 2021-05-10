import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class SentenceTest {

    Sentence sentence = new Sentence();

    @Test
    void removeSpecialChars_GivenSymbols() throws IOException {
        //given
        String input = "!@#@$@!%@#><)(";
        List<String> resultExpected = Arrays.asList("");

        //when
        List<String> result = sentence.removeSpecialChars(input);

        //then
        Assertions.assertEquals(resultExpected, result);
    }

    @Test
    void removeSpecialChars_GivenNormalSentence() throws IOException {
        //given
        String input = "Mam go to school";
        List<String> resultExpected = Arrays.asList("Mam", "go", "to", "school");

        //when
        List<String> result = sentence.removeSpecialChars(input);

        //then
        Assertions.assertEquals(resultExpected, result);
    }

    @Test
    void removeSpecialChars_GivenNormalSentenceWithSymbolAndSpaces() throws IOException {
        //given
        String input = "()()   Mam @go@ @to <> school!!!";
        List<String> resultExpected = Arrays.asList("Mam", "go", "to", "school");

        //when
        List<String> result = sentence.removeSpecialChars(input);

        //then
        Assertions.assertEquals(resultExpected, result);
    }

    @Test
    void removeSpecialChars_GivenEmptyString() throws IOException {
        //given
        String input = "";
        List<String> resultExpected = Arrays.asList("");

        //when
        List<String> result = sentence.removeSpecialChars(input);

        //then
        Assertions.assertEquals(resultExpected, result);
    }

    @Test
    void countChars_givenListWithNormalWords() {
        //given
        List<String> input = Arrays.asList("Mam", "go", "to", "school");
        int resultExpected = 13;

        //when
        int result = sentence.countChars(input);

        //then
        Assertions.assertEquals(resultExpected, result);
    }

    @Test
    void countChars_givenListWithOneWord() {
        //given
        List<String> input = Arrays.asList("Mam");
        int resultExpected = 3;

        //when
        int result = sentence.countChars(input);

        //then
        Assertions.assertEquals(resultExpected, result);
    }

    @Test
    void countChars_givenEmptyList() {
        //given
        List<String> input = Arrays.asList("");
        int resultExpected = 0;

        //when
        int result = sentence.countChars(input);

        //then
        Assertions.assertEquals(resultExpected, result);
    }

    @Test
    void countAllSpecials() {
    }

    @Test
    void printRapport() {
    }
}