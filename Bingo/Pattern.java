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

    private boolean checkRows(CardHandler card) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (card.cardSpaces[y*5+x] != "XX") {
                    break;
                }
                card.cardSpaces[y*5+x] = "--";
                if (x == 4) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkColumns(CardHandler card) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (card.cardSpaces[y*5+x] != "XX") {
                    break;
                }
                card.cardSpaces[y*5+x] = "--";
                if (y == 4) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkDiagonals(CardHandler card) {
        for (int i = 0; i < 5; i++) {
            if (card.cardSpaces[i*5+i] != "XX") {
                break;
            }
            if (i != 2) {
                card.cardSpaces[i*5+i] = "--";
            }
            if (i == 4) {
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (card.cardSpaces[i*5+(4-i)] != "XX") {
                break;
            }
            if (i != 2) {
                card.cardSpaces[i*5+(4-i)] = "--";
            }
            if (i == 4) {
                return true;
            }
        }

        return false;
    }

    private boolean checkCustom(CardHandler card) {
        for (int i = 0; i < customPattern.length; i++) {
            if (card.cardSpaces[customPattern[i][1]*5+customPattern[i][0]] != "XX") {
                break;
            } 
            card.cardSpaces[customPattern[i][1]*5+customPattern[i][0]] = "--";
            if (i == customPattern.length - 1) {
                return true;
            }
        }

        return false;
    }

    public void resetCheckedSpaces(CardHandler card) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (card.cardSpaces[y*5+x] == "--") {
                    card.cardSpaces[y*5+x] = "XX";
                }
            }
        }
    }

    public boolean checkCard(CardHandler card) {
        switch (patternType) {
            case "Row Pattern":
                return checkRows(card);
            case "Column Pattern":
                return checkColumns(card);
            case "Diagonal Pattern":
                return checkDiagonals(card);
            case "Custom Pattern":
                return checkCustom(card);
            default:
                break;
        }

        return false;
    }
}
