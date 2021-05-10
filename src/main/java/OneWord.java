import java.util.Arrays;

public class OneWord {

    private String specialChars = "";
    private int lengthOfWord;
    private int numberOfSpecial;
    private int numberOfAllSpecial;
    private int numberOfAllChars;

    public int getNumberOfAllSpecial() {
        return numberOfAllSpecial;
    }

    public void setNumberOfAllSpecial(int numberOfAllSpecial) {
        this.numberOfAllSpecial = numberOfAllSpecial;
    }

    public int getNumberOfAllChars() {
        return numberOfAllChars;
    }

    public void setNumberOfAllChars(int numberOfAllChars) {
        this.numberOfAllChars = numberOfAllChars;
    }

    public OneWord() {
    }

    public String getSpecialChars() {
        return specialChars;
    }

    public void setSpecialChars(String specialChars) {
        this.specialChars = specialChars;
    }

    public int getLengthOfWord() {
        return lengthOfWord;
    }

    public void setLengthOfWord(int lengthOfWord) {
        this.lengthOfWord = lengthOfWord;
    }

    public int getNumberOfSpecial() {
        return numberOfSpecial;
    }

    public void setNumberOfSpecial(int numberOfSpecial) {
        this.numberOfSpecial = numberOfSpecial;
    }

    private double devicePart() {
        double part = getNumberOfSpecial() * 100d / numberOfAllSpecial;
        part = Math.round(part);
        return part / 100;
    }

    @Override
    public String toString() {
        return "(" + Arrays.asList(specialChars.split("")).toString() + ", " +
                lengthOfWord + ") = " + devicePart() +
                " (" + numberOfSpecial + "/" + numberOfAllSpecial + ")";
    }

}
