package Bingo;

public class CardHandler {
    private String name;
    private int[] cardValues;
    public String[] cardSpaces;

    // Generates a CardHandler Class using a name and a comma separated unformatted (not worring about extra spaces) string.
    // The values obtained from [cardValues] are then turned into a 1 dimensional array.
    public CardHandler(String name, String cardValues) {
        this.name = name;

        String[] temp = cardValues.split(",");
        this.cardValues = new int[temp.length];
        this.cardSpaces = new String[temp.length];

        for (int i = 0; i < temp.length; i++) {
            this.cardValues[i] = Integer.parseInt(temp[i].replace(" ", ""));
            this.cardSpaces[i] = temp[i].replace(" ", "");
        }
    }

    // First checks to see if [location] is formatted such that it looks like [Letter#] and whether that exists in [cardSpaces].
    // Next it checks to see if [location] is formatted such that it looks like [LetterLetter] which gets replaced regardless so long as [2 Letters] both exist in [BINGO].
    // [true] is returned if the location was stamped or [false] if it was not.
    public boolean stampLocation(String location) {
        for (int i = 0; i < cardSpaces.length; i++) {
            if (location.equals("BINGO".charAt(i % 5) + cardSpaces[i])) {
                cardSpaces[i] = "XX";
                return true;
            }
        }

        if (location.length() == 2) {
            int x = "BINGO".indexOf(location.charAt(0));
            int y = "BINGO".indexOf(location.charAt(1));
    
            if ((x == -1) || (y == -1)) {
                return false;
            }
    
            cardSpaces[y*5+x] = "XX";
            return true;
        }

        return false;
    }

    // Checks to see if [value] has been added to [calledSpaces] returning [true] if thats the case or [false] if not.
    private boolean validateValue(String value, String[] calledSpaces) {
        int i = 0;
        while (((i < 75) && calledSpaces[i] != null)) {
            if (calledSpaces[i].equals(value)) {
                return true;
            }
            i++;
        }

        return false;
    }

    // Checks all the possible winning conditions to see if there was a bingo which is both valid and only contains spaces which have been called and inside [calledSpaces]
    // returns [true] at the first instance and [false] if the end of the method is reached without finding anything.
    public boolean validateCard(String[] calledSpaces) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (cardSpaces[y*5+x] == "XX") {
                    if (!validateValue("BINGO".charAt(x) + Integer.toString(cardValues[y*5+x]), calledSpaces)) {
                        cardSpaces[y*5+x] = "||";
                    }
                }
            }
        }
        
        // Check for Columns
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (!validateValue("BINGO".charAt(x) + Integer.toString(cardValues[y*5+x]), calledSpaces)) {
                    break;
                }
                if (cardSpaces[y*5+x] != "XX") {
                    break;
                }
                if (y == 4) {
                    return true;
                }
            }
        }

        // Checks for TopLeft to BottomRight
        for (int i = 0; i < 5; i++) {
            if (!validateValue("BINGO".charAt(i) + Integer.toString(cardValues[i*5+i]), calledSpaces)) {
                break;
            }
            if (cardSpaces[i*5+i] != "XX") {
                break;
            }
            if (i == 4) {
                return true;
            }
        }

        // Checks for TopRight to BottomLeft
        for (int i = 0; i < 5; i++) {
            if (!validateValue("BINGO".charAt(i) + Integer.toString(cardValues[i*5+(4-i)]), calledSpaces)) {
                break;
            }
            if (cardSpaces[i*5+(4-i)] != "XX") {
                break;
            }
            if (i == 4) {
                return true;
            }
        }

        return false;
    }

    // returns a horizontally cut out row of the cards values.
    public String[] getRowSpaces(int row) {
        String[] temp = new String[5];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cardSpaces[(row*5)+i];
        }
        return temp;
    }

    // returns the cards Name.
    public String getName() {
        return name;
    }
}