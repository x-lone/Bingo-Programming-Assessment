package Bingo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class GameHander {
    private CardHandler[] allCards;
    private int totalCardCount;
    private CardHandler[] userCards;

    public GameHander(String path) {
        allCards = new CardHandler[20];
        CreateCardsFromFullTxt(path);
    }

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

    private void SelectUserCards(int cardCount) {
        Random rand = new Random();

        CardHandler[] tempCards = allCards.clone();

        userCards = new CardHandler[cardCount];
        for (int i = 0; i < cardCount; i++) {
            int selectedIndex = rand.nextInt(totalCardCount - i);
            userCards[i] = tempCards[selectedIndex];
            CardHandler[] temp = new CardHandler[tempCards.length - 1];
            for (int x = 0, y = 0; x < tempCards.length; x++) {
                if (x != selectedIndex) {
                    temp[y++] = tempCards[x];
                }
            }
            tempCards = temp;
        }
    }

    private void DrawUserCards() {
        for (int card = 0; card < userCards.length; card++) {
            System.out.print("------" + userCards[card].GetName() + "------   ");
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

        for (int y = 0; y < 5; y++) {
            for (int card = 0; card < userCards.length; card++) {
                int[] row = userCards[card].GetRow(y);
                System.out.print("BINGO".charAt(y) + "|");
                for (int x = 0; x < 5; x++) {
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

    private void Random() {

    }

    private void Manual() {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many cards would you like? (1-3)");
        int cardCount = scanner.nextInt();
        scanner.close();

        SelectUserCards(cardCount);
        
        DrawUserCards();
    }
}
