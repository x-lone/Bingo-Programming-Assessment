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

    public boolean checkRow(CardHandler card) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                
            }
        }

        return false;
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
