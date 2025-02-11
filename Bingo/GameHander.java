package Bingo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameHander {
    private CardHandler[] allCards;
    private int totalCardCount;
    private CardHandler[] userCards;

    private boolean CreateCardsFromFullTxt(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String name = line;
                String values = "";

                line = reader.readLine();
                while (!line.isEmpty()) {
                    values += line + ",";
                    line = reader.readLine();
                }

                allCards[i] = new CardHandler(name, values);
                i++;
            }
            totalCardCount = i;
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return true;
        }

        return false;
    }

    public void DrawUserCards() {
        for (int card = 0; card < userCards.length; card++) {
            System.out.print("------" + allCards[card].GetName() + "------   ");
        }
        System.out.println();
        for (int card = 0; card < userCards.length; card++) {
            System.out.print("    B I N G O       ");
        }
        System.out.println();
        for (int card = 0; card < userCards.length; card++) {
            System.out.print("-+--+--+--+--+--+   ");
        }
        System.out.println();

        for (int y = 0; y < userCards.length; y++) {
            for (int card = 0; card < userCards.length; card++) {
                int[] row = allCards[card].GetRow(y);
                System.out.print("BINGO".charAt(y) + "|");
                for (int x = 0; x < userCards.length; x++) {
                    int value = row[x];
                    System.out.printf("%2d|", value);
                }
                System.out.print("   ");
            }
            System.out.println();
            for (int card = 0; card < userCards.length; card++) {
                System.out.print("-+--+--+--+--+--+   ");
            }
            System.out.println();
        }
    }

    private void ImportCards(int cardCount) {
        // cards = new CardHandler[cardCount];
        // for (int i = 0; i < cardCount; i++) {
        //     cards[i] = new CardHandler();
        // }
    }

    private void Random() {

    }

    private void Manual() {

    }

    public GameHander(String path) {
        allCards = new CardHandler[20];
        userCards = new CardHandler[0];

        CreateCardsFromFullTxt(path);
        DrawUserCards();
    }

    public void run() {

    }
}
