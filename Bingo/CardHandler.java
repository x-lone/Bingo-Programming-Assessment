package Bingo;

public class CardHandler {
    private String name;
    private int[] cardValues;
    private String[] cardSpaces;

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

    public void stampLocation(int x, int y) {
        cardSpaces[y*5+x] = "XX";
    }


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


    public boolean validateCard(String[] calledSpaces) {
        // Checks for Rows
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (!validateValue("BINGO".charAt(x) + Integer.toString(cardValues[y*5+x]), calledSpaces)) {
                    break;
                }
                if (cardSpaces[y*5+x] != "XX") {
                    break;
                }
                if (x == 4) {
                    return true;
                }
            }
        }

        // Check for Columns
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
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
            if (cardSpaces[i*5+i] != "XX") {
                break;
            }
            if (i == 4) {
                return true;
            }
        }

        // Checks for TopRight to BottomLeft
        for (int i = 0; i < 5; i++) {
            if (cardSpaces[i*5+(4-i)] != "XX") {
                break;
            }
            if (i == 4) {
                return true;
            }
        }

        return false;
    }

    public String[] getRowSpaces(int row) {
        String[] temp = new String[5];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = cardSpaces[(row*5)+i];
        }
        return temp;
    }

    public String getName() {
        return name;
    }
}