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

    public void undoValidateCard() {
        for (int i = 0; i < 25; i++) {
            if (cardSpaces[i] == "||") {
               cardSpaces[i] = "XX";
            }
        }   
    }

    public void validateCard(String[] calledSpaces) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (cardSpaces[y*5+x] == "XX") {
                    if (!validateValue("BINGO".charAt(x) + Integer.toString(cardValues[y*5+x]), calledSpaces)) {
                        cardSpaces[y*5+x] = "||";
                    }
                }
            }
        }
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