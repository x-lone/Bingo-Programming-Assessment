package Bingo;

public class Pattern {
    private String patternType = null;
    private int[][] customPattern = null;

    public Pattern(String patternType) {
        this.patternType = patternType;
    }

    public Pattern(int[][] coordinates) {
        this.customPattern = coordinates;
    }

    public boolean checkCard(CardHandler card) {

        return false;
    }
}
