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

    private void randomSelectUserCards(int cardCount) { // maybe add another function to this
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

    private void manualSelectUserCards(Scanner scanner, int cardCount) {
        userCards = new CardHandler[cardCount];
        String userInput = null;
        boolean saveValue;
        boolean foundCard = false;
        int userCardsIndex = 0;

        System.out.println("Enter the Name of the Card you Would Like to Use.");
        while ((userCardsIndex < cardCount) && (userCards[userCardsIndex] == null)) {
            saveValue = true;
            userInput = scanner.nextLine();
            int allCardsIndex = 0;
            while (allCards[allCardsIndex] != null) {
                if (userInput.equals(allCards[allCardsIndex].getName())) {
                    foundCard = true;
                    int userCardsRescanIndex = 0;
                    while (userCards[userCardsRescanIndex] != null) {
                        if (userInput.equals(userCards[userCardsRescanIndex].getName())) {
                            System.out.println("Value: " + userInput + " has Already Been Taken");
                            saveValue = false;
                            break;
                        }
                        userCardsRescanIndex++;
                    }
                    if (saveValue) {
                        userCards[userCardsRescanIndex] = allCards[allCardsIndex];
                        userCardsIndex++;
                    }
                    break;
                }
                allCardsIndex++;
            }
            if (!foundCard) {
                System.out.println("Value: " + userInput + " is Not a Valid Card Name");
            }
        }
    }

    private void drawCards(CardHandler[] cards) {
        for (int card = 0; card < cards.length ; card++) {
            System.out.print("---------- " + cards[card].getName() + " ----------   ");
        }
        System.out.println();
        for (int card = 0; card < cards.length; card++) {
            System.out.print("   B    I    N    G    O      ");
        }
        System.out.println();
        for (int card = 0; card < cards.length; card++) {
            System.out.print("-+----+----+----+----+----+   ");
        }
        System.out.println();

        for (int y = 0; y < 5; y++) {
            for (int card = 0; card < cards.length; card++) {
                String[] row = cards[card].getRowSpaces(y);
                System.out.print("BINGO".charAt(y) + "|");
                System.out.printf(" %2s | %2s | %2s | %2s | %2s |", row[0], row[1], row[2], row[3], row[4]);
                System.out.print("   ");
            }
            System.out.println();
            for (int card = 0; card < cards.length; card++) {
                System.out.print("-+----+----+----+----+----+   ");
            }
            System.out.println();
        }
    }

    private void drawCard(CardHandler card) {
        System.out.print("---------- " + card.getName() + " ----------   \n");
        System.out.print("   B    I    N    G    O      \n");
        System.out.print("-+----+----+----+----+----+   \n");

        for (int y = 0; y < 5; y++) {
            String[] row = card.getRowSpaces(y);
            System.out.print("BINGO".charAt(y) + "|");
            System.out.printf(" %2s | %2s | %2s | %2s | %2s |", row[0], row[1], row[2], row[3], row[4]);
            System.out.print("\n-+----+----+----+----+----+   \n");
        }
    }

    private String randomCaller(int depth) { // might be able to change the depth checker also check if out of bounds
        Random rand = new Random();

        int value = rand.nextInt(75) + 1;

        String newSpace = "BINGO".charAt((value - 1) / 15) + Integer.toString(value);
        
        int i = 0;
        while (calledSpaces[i] != null) {
            if (calledSpaces[i].equals(newSpace)) {
                newSpace = randomCaller(depth + 1);
            }
            i++;
        }
        if (depth == 0) {
            calledSpaces[i] = newSpace;
        }

        return newSpace;
    }

    private boolean maualCaller(String userInput) {
        if (userInput.length() < 2) {
            return false;
        }

        int value = -1;

        try {
            value = Integer.parseInt(userInput.substring(1, userInput.length()));
        } catch (Exception e) {
            return false;
        }

        if ((value > 75) || (value < 1)) {
            return false;
        }

        if (userInput.equals(Character.toString("BINGO".charAt((value - 1) / 15)) + value)) {
            return true;
        }
        
        int i = 0;
        while (calledSpaces[i] != null) {
            if (calledSpaces[i].equals(userInput)) {
                return false;
            }
            i++;
        }

        return false;
    }

    private CardHandler[] returnBingos() {
        CardHandler[] validatedCards = new CardHandler[userCards.length];

        int i = 0;
        for (CardHandler card : userCards) {
            if (card.validateCard(calledSpaces)) {
                System.out.println("true");
                validatedCards[i] = card;
                i++;
            }
        }

        CardHandler[] returnCards = new CardHandler[i];
        for (int j = 0; j < i; j++) {
            returnCards[j] = validatedCards[j];
        }

        return returnCards;
    }

    private CardHandler isValidCard(String userInput) {
        for (CardHandler card : userCards) {
            if (card.getName().equals(userInput)) {
                return card;
            }
        }

        try {
            return userCards[Integer.valueOf(userInput)];
        } catch (Exception e) {
            return null;
        }
    }

    private void random(Scanner scanner) {
        String callerValue = randomCaller(0);
        String userInput = null;

        while (true) {
            drawCards(userCards);
            System.out.println("Called Position: " + callerValue);

            System.out.println("Enter Bingo to check for Bingo.");
            System.out.println("What Card Would you Like to Stamp? (Card Name or Index): ");
            System.out.println("Enter Call to call a new Location.");
            userInput = scanner.nextLine();

            if (userInput.equals("Bingo")) {
                CardHandler[] bingos = returnBingos();
                if (bingos.length > 0) {
                    System.out.println("Bingos Were Found on the Following Cards:");
                    drawCards(bingos);
                    break;
                } else {
                    System.out.println("No Bingos Found");
                    scanner.nextLine();
                    continue;
                }
            }

            if (userInput.equals("Call")) {
                callerValue = randomCaller(0);
                continue;
            }

            CardHandler selectedCard = isValidCard(userInput);
            if (selectedCard == null) {
                System.out.println("Card: " + userInput + " is an Invalid Input");
                scanner.nextLine();
                continue;
            }

            drawCard(selectedCard);
            System.out.println("Called Position: " + callerValue);

            System.out.println("What Position Would you Like to Stamp? (B-OB-O/B-0#)");
            String loaction = scanner.nextLine();

            if (!selectedCard.stampLocation(loaction)) {
                System.out.println("Location: " + loaction + " is Not a Valid Location");
                scanner.nextLine();
                continue;
            }
        }
    }

    private void manual(Scanner scanner) {
        System.out.println("Input the First Location to Call: ");
        String userInput = scanner.nextLine();
        String callerValue = userInput;

        while (!maualCaller(callerValue)) {
            System.out.println("Value: " + callerValue + " is an Invalid Caller Value");
            callerValue = scanner.nextLine();
        }

        while (true) {
            drawCards(userCards);
            System.out.println("Called Position: " + callerValue);

            System.out.println("Enter Bingo to check for Bingo.");
            System.out.println("What Card Would you Like to Stamp? (Card Name or Index): ");
            System.out.println("Enter Call to call a new Location.");
            userInput = scanner.nextLine();

            if (userInput.equals("Bingo")) {
                CardHandler[] bingos = returnBingos();
                if (bingos.length > 0) {
                    System.out.println("Bingos Were Found on the Following Cards:");
                    drawCards(bingos);
                    break;
                } else {
                    System.out.println("No Bingos Found");
                    scanner.nextLine();
                    continue;
                }
            }

            if (userInput.equals("Call")) {
                System.out.println("Input the Next Location to Call: ");
                callerValue = scanner.nextLine();
                while (!maualCaller(callerValue)) {
                    System.out.println("Value: " + callerValue + "is an Invalid Caller Value or Has Already Been Called");
                    callerValue = scanner.nextLine();
                }
                continue;
            }

            CardHandler selectedCard = isValidCard(userInput);
            if (selectedCard == null) {
                System.out.println("Card: " + userInput + " is an Invalid Input");
                scanner.nextLine();
                continue;
            }

            drawCard(selectedCard);
            System.out.println("Called Position: " + callerValue);

            System.out.println("What Position Would you Like to Stamp? (B-OB-O/B-0#)");
            String loaction = scanner.nextLine();

            if (!selectedCard.stampLocation(loaction)) {
                System.out.println("Location: " + loaction + " is Not a Valid Location");
                scanner.nextLine();
                continue;
            }
        }
    }

    private void resetGame() {
        allCards = new CardHandler[20];
        calledSpaces = new String[75];
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many cards would you like? (1-4)");
        int cardCount = -1;
        String userInput = null;

        while (cardCount == -1) {
            userInput = scanner.nextLine();

            try {
                cardCount = Integer.parseInt(userInput);
                if ((cardCount > 4) || (cardCount < 1)) {
                    System.out.println("Value: " + userInput + " is an Invalid Card Amount");
                    cardCount = -1;
                }
            } catch (Exception e) {
                System.out.println("Value: " + userInput + " is an Invalid Card Amount");
                cardCount = -1;
            }
        }

        System.out.println("Would you like to play Random or Manual?");
        userInput = scanner.nextLine();
        while (!userInput.equals("Random") && !userInput.equals("Manual")) {
            System.out.println("Value: " + userInput + " is an Invalid Game Mode");
            userInput = scanner.nextLine();
        }

        if (userInput.equals("Random")) {
            randomSelectUserCards(cardCount);
            random(scanner);
        }

        if (userInput.equals("Manual")) {
            manualSelectUserCards(scanner, cardCount);
            manual(scanner);
        }

        scanner.close();
    }   
}