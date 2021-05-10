import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class General {

    @Test
    void correctExample() throws IOException {
        //given
        String text = "I love to work in global logic!";
        String resultExpected = """
                                
                ([i], 1) = 0.07 (1/15)
                ([o], 2) = 0.07 (1/15)
                ([o], 4) = 0.07 (1/15)
                ([i], 2) = 0.07 (1/15)
                ([l, o], 4) = 0.13 (2/15)
                ([l, o, g], 6) = 0.27 (4/15)
                ([l, o, g, i, c], 5) = 0.33 (5/15)
                TOTAL Frequency: 0.63 (15/24)""";
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        //when
        String result = new Sentence().printRapport();
        //then
        assertEquals(resultExpected, result);
    }

    @Test
    void correctExampleWithEmptyWords() throws IOException {
        //given
        String text = "aAaA BbBb CcCc";
        String resultExpected = """
                
                ([], 4) = 0.0 (0/4)
                ([], 4) = 0.0 (0/4)
                ([c], 4) = 1.0 (4/4)
                TOTAL Frequency: 0.33 (4/12)""";
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        //when
        String result = new Sentence().printRapport();
        //then
        assertEquals(resultExpected, result);
    }

    @Test
    void exampleWithSpecialChars() throws IOException {
        //given
        String text = "!@#$%^{}()";
        String resultExpected = """
                                
                ([], 0) = 0.0 (0/0)
                TOTAL Frequency: 0.0 (0/0)""";
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        //when
        String result = new Sentence().printRapport();
        //then
        assertEquals(resultExpected, result);
    }

    @Test
    void exampleWithALotOfSpaces() throws IOException {
        //given
        String text = "     The analysis()       should be    case   @#!     ";
        String resultExpected = """
                                
                ([], 3) = 0.0 (0/5)
                ([], 2) = 0.0 (0/5)
                ([c], 4) = 0.2 (1/5)
                ([l, i], 8) = 0.4 (2/5)
                ([l, o], 6) = 0.4 (2/5)
                TOTAL Frequency: 0.22 (5/23)""";
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        //when
        String result = new Sentence().printRapport();
        //then
        assertEquals(resultExpected, result);
    }

    @Test
    void exampleWithOneSpace() throws IOException {
        //given
        String text = " ";
        String resultExpected = """
                                
                ([], 0) = 0.0 (0/0)
                TOTAL Frequency: 0.0 (0/0)""";
        System.setIn(new ByteArrayInputStream(text.getBytes()));
        //when
        String result = new Sentence().printRapport();
        //then
        assertEquals(resultExpected, result);
    }

}
