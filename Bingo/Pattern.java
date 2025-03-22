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
                if (card.cardSpaces[y*5+x] != "XX") {
                    break;
                }
                if (x == 4) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkCard(CardHandler card) {
        switch (patternType) {
            case "Row Pattern":
                return checkRow(card);
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
