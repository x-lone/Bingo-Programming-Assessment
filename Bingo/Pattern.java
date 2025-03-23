package Bingo;

public class Pattern {
    private String patternType = null;
    private int[][] customPattern = null;

    // Takes in [String patternType] and uses the value to set the value of the global varable (patternType).
    public Pattern(String patternType) {
        this.patternType = patternType;
    }

    // Works similarly to the above Pattern method but take in [int[][] coordinates] which repalces the global (customPattern) and sets (patternType) to "Custom Pattern".
    public Pattern(int[][] coordinates) {
        this.patternType = "Custom Pattern";
        this.customPattern = coordinates;
    }

    // Loops through all the rows of [CardHandler card] checkings to see if there is a bingo returning true if found and false if not. In doing this the method also
    // loops through each index of the row and if its value is "XX" then its replaced with "--".
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

    // Loops through all the columns of [CardHandler card] checkings to see if there is a bingo returning true if found and false if not. In doing this the method also
    // loops through each index of the columns and if its value is "XX" then its replaced with "--".
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

    // Loops through all the diagonals of [CardHandler card] checkings to see if there is a bingo returning true if found and false if not. In doing this the method also
    // loops through each index of the diagonals and if its value is "XX" then its replaced with "--" except for the middle space.
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

    // Loops through each index of the (customPattern) and compares the value of [CardHandler card] using the coordinates from (customPattern) with "XX" if all spaces contain
    // "XX" then return true else return false. In doing this the method also loops through each index of the diagonals and if its value is "XX" then its replaced with "--".
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

    // Undos the action of changing "XX" to "--" on [CardHandler card] done by all the checks.
    public void resetCheckedSpaces(CardHandler card) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (card.cardSpaces[y*5+x] == "--") {
                    card.cardSpaces[y*5+x] = "XX";
                }
            }
        }
    }

    // Takes in [CardHandler card] and ues (patternType) to check for bingos using only that (patternType) returning true if found and false if not.
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
        }

        return false;
    }
}
