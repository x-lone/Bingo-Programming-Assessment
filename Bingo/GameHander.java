package Bingo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

// TODO
// X. Flesh out Card Stamping 
// X. Check Win Condition 
// X. Get User Input 
// 4. Constrain User Input
// 5. Flesh out Game Modes

public class GameHander {
    private CardHandler[] allCards;
    private int totalCardCount;
    private CardHandler[] userCards;
    private String[] calledSpaces;

    public GameHander() {
        resetGame();
    }

    public boolean createCardsFromTxt(String path) {
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
            return false;
        }

        return true;
    }

    private void selectUserCards(int cardCount) { // maybe add another function to this
        Random rand = new Random();

        userCards = new CardHandler[cardCount];
        CardHandler[] availableCards = allCards.clone();
        
        for (int i = 0; i < cardCount; i++) {
            int selectedIndex = rand.nextInt(totalCardCount - i);
            userCards[i] = availableCards[selectedIndex];

            CardHandler[] temp = new CardHandler[availableCards.length - 1];
            for (int x = 0, y = 0; x < availableCards.length; x++) {
                if (x != selectedIndex) {
                    temp[y++] = availableCards[x];
                }
            }

            availableCards = temp;
        }
    }

    private void drawUserCards() { // this is really long see what you can do
        for (int card = 0; card < userCards.length; card++) {
            System.out.print("------" + userCards[card].getName() + "------   ");
        }
        System.out.println();
        for (int card = 0; card < userCards.length; card++) {
            System.out.print("  B  I  N  G  O     ");
        }
        System.out.println();
        for (int card = 0; card < userCards.length; card++) {
            System.out.print("-+--+--+--+--+--+   ");
        }
        System.out.println();

        for (int y = 0; y < 5; y++) {
            for (int card = 0; card < userCards.length; card++) {
                String[] row = userCards[card].getRowSpaces(y);
                System.out.print("BINGO".charAt(y) + "|");
                for (int x = 0; x < 5; x++) {
                    String value = row[x];
                    System.out.printf("%2s|", value);
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

    private String caller(int depth) { // might be able to change the depth checker
        Random rand = new Random();

        int value = rand.nextInt(75) + 1;

        String newSpace = "BINGO".charAt((value - 1) / 15) + Integer.toString(value);
        
        int i = 0;
        while (calledSpaces[i] != null) {
            if (calledSpaces[i].equals(newSpace)) {
                newSpace = caller(depth + 1);
            }
            i++;
        }
        if (depth == 0) {
            calledSpaces[i] = newSpace;
        }

        return newSpace;
    }

    private void random() {

    }

    private void manual() {

    }

    private void resetGame() {
        allCards = new CardHandler[20];
        calledSpaces = new String[75];
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        selectUserCards(1);

        // System.out.println("How many cards would you like? (1-3)");
        // int cardCount = scanner.nextInt();

        // scanner.close();

        while (true) {
            drawUserCards();

            String callerValue = caller(0);
            System.out.println(callerValue);

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if ((x == -1) && (y == -1)) {
                break;
            }
            if ((x == -1) || (y == -1)) {
                continue;
            }

            userCards[0].stampLocation(x, y);
        }
        System.out.println(userCards[0].validateCard(calledSpaces));

        scanner.close();
    }
}