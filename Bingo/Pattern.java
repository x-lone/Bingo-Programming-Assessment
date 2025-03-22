package Bingo;

public class Pattern {
    private String patternType = null;
    private int[][] customPattern = null;

    public Pattern(String patternType) {
        this.patternType = patternType;
    }

    public Pattern(int[][] coordinates) {
        this.patternType = "Custom Pattern";
        this.customPattern = coordinates;
    }

    public boolean checkCard(CardHandler card) {
        switch (patternType) {
            case "Row Pattern":
                break;
            case "Column Pattern":
                break;
            case "Diagonal Pattern":
                break;
            case "Custom Pattern":
                break;
            default:
                break;
        }

        return false;
    }
}
