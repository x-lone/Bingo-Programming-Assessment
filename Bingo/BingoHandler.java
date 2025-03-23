package Bingo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BingoHandler {
    private int totalCardCount;
    public CardHandler[] allCards;
    private CardHandler[] userCards;
    private String[] calledSpaces;
    private List<Pattern> validPatterns = new ArrayList<>();

    // [allCards] and [calledSpaces] default to be emptied out so they can be filled later.
    public BingoHandler() {
        allCards = new CardHandler[20];
        calledSpaces = new String[75];
    }

    // Generates Cards from the [path to a formatted text file] adding all of them to [allCards] and returning [true] if successful or [false] if there was a problem.
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
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // Randomly Selects an amount of cards specified by [cardCount] from [allCards] with no repeats replacing [userCards] with the resulting array.
    private void randomSelectUserCards(int cardCount) {
        Random rand = new Random();

        userCards = new CardHandler[cardCount];
        CardHandler[] availableCards = allCards.clone();
        
        for (int i = 0; i < cardCount; i++) {
            int selectedIndex = rand.nextInt(totalCardCount - i);
            userCards[i] = availableCards[selectedIndex];

            CardHandler[] updatedAvailableCards = new CardHandler[availableCards.length - 1];
            for (int x = 0, y = 0; x < availableCards.length; x++) {
                if (x != selectedIndex) {
                    updatedAvailableCards[y++] = availableCards[x];
                }
            }

            availableCards = updatedAvailableCards;
        }
    }

    // Because of input checks this method looks more complicated than it really is. Basically it checks for empty indexs in [userCards] uses the [scanner]
    // to ask the user to input the name of a card then checks if it exists and lastly checks if the card is already in [userCards].
    private void manualSelectUserCards(Scanner scanner, int cardCount) {
        userCards = new CardHandler[cardCount];
        String userInput;

        boolean saveValue;
        boolean foundCard;

        int userCardsIndex = 0;

        while ((userCardsIndex < cardCount) && (userCards[userCardsIndex] == null)) {
            System.out.println("Enter the Name of a Card you Would Like to Use.");
            userInput = scanner.nextLine();

            saveValue = true;
            foundCard = false;

            for (int allCardsIndex = 0; allCardsIndex < totalCardCount; allCardsIndex++) {
                if (userInput.equals(allCards[allCardsIndex].getName())) {
                    int userCardsRescanIndex = 0;

                    foundCard = true;

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
            }

            if (!foundCard) {
                System.out.println("Value: " + userInput + " is Not a Valid Card Name");
            }
        }
    }

    // Draws all of [cards] cards onto screen formatted and next to each other horizontally.
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

    // Works similar to [drawCards] minus the fact that it can only display a single card at once.
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

    // Selects a random call space then checks [calledSpaces] to ensure it hasn't already been called if it has been called the method will call itself. if [i] reaches
    // beyond 74 the total number of possible calls -1 it will return [null] to signify it's out of bounds. [depth] is just there so that values are only replaced
    // when wanted. The new caller space [newSpace] is returned on success.
    private String randomCaller(int depth) {
        Random rand = new Random();
        int value = rand.nextInt(75) + 1;

        String newSpace = "BINGO".charAt((value - 1) / 15) + Integer.toString(value);
        
        int i = 0;
        while ((i <= 74) && (calledSpaces[i] != null)) {
            if (calledSpaces[i].equals(newSpace)) {
                newSpace = randomCaller(depth + 1);
            }
            i++;
        }

        if ((i >= 74) || (newSpace == null)) {
            return null;
        }

        if (depth == 0) {
            calledSpaces[i] = newSpace;
        }

        return newSpace;
    }

    // Runs a series of checks to determine if [userInput] is a valid caller Value. Returns [true] while also adding it to called spaces if valid and [false] if not.
    private boolean maualCaller(String userInput) {
        if (userInput.length() < 2) {
            return false;
        }

        int value;

        try {
            value = Integer.parseInt(userInput.substring(1, userInput.length()));
        } catch (Exception e) {
            return false;
        }

        if ((value > 75) || (value < 1)) {
            return false;
        }

        int i = 0;
        while (calledSpaces[i] != null) {
            if (calledSpaces[i].equals(userInput)) {
                return false;
            }

            i++;
        }

        if (userInput.equals(Character.toString("BINGO".charAt((value - 1) / 15)) + value)) {
            calledSpaces[i] = userInput;
            return true;
        }

        return false;
    }

    // Checks to see if [userInput] assumably a card name is a valid cardName or not (can also take in an index of [userCards] just because).
    // Finds and [returns the corresponding card] on success and [null] on a failure to find an associated card.
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

    // Goes through userCards using the card's validateCard method to determine if it contains a Bingo. If so adds it to return cards and skips it if not.
    // Either an [array of cards with Bingo] will be returned or an [empty array].
    private CardHandler[] returnBingos() {
        CardHandler[] validatedCards = new CardHandler[userCards.length];

        int i = 0;
        for (CardHandler card : userCards) {
            card.validateCard(calledSpaces);
            if (countBingos(card) > 0) {
                validatedCards[i] = card;
                i++;
            }
            card.undoValidateCard();
        }

        CardHandler[] returnCards = new CardHandler[i];
        for (int j = 0; j < i; j++) {
            returnCards[j] = validatedCards[j];
        }

        return returnCards;
    }

    public void addPattern(String patternType) {
        Pattern newPattern = null;
        switch (patternType) {
            case "Row Pattern":
            case "Column Pattern":
            case "Diagonal Pattern":
                newPattern = new Pattern(patternType);
                validPatterns.add(newPattern);
                break;
        }
    }

    public void addPattern(int[][] coordinates) {
        Pattern newPattern = null;
        newPattern = new Pattern(coordinates);
        validPatterns.add(newPattern);
    }

    public int countBingos(CardHandler card) {
        int bingos = 0;

        for (Pattern pattern : validPatterns) {
            while (pattern.checkCard(card)) {
                bingos++;
            }
            pattern.resetCheckedSpaces(card);
        }

        return bingos;
    }

    // Holds all the terminal prompt messages and checks corresponding to a randomMode game.
    private void randomMode(Scanner scanner) {
        String callerValue = randomCaller(0);
        String userInput;

        while (true) {
            System.out.println("\n");
            drawCards(userCards);
            System.out.println("Called Position: " + callerValue + "\n");

            System.out.println("Enter \"Bingo\" to check for Bingo.");
            System.out.println("Enter \"Call\" to call a new Location.");
            System.out.println("Or you can Stamp a Card. (Card Name or Index): ");
            userInput = scanner.nextLine();

            if (userInput.equals("Bingo")) {
                CardHandler[] bingos = returnBingos();
                if (bingos.length > 0) {
                    System.out.println("\n\nBingos Were Found on the Following Cards:");
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
                if (callerValue == null) {
                    System.out.println("All Possible Calls have been Called");
                }
                continue;
            }

            CardHandler selectedCard = isValidCard(userInput);
            if (selectedCard == null) {
                System.out.println("Card: " + userInput + " is an Invalid Input");
                scanner.nextLine();
                continue;
            }

            System.out.println("\n");
            drawCard(selectedCard);
            System.out.println("Called Position: " + callerValue + "\n");

            System.out.println("What Position Would you Like to Stamp? (B-OB-O/B-0#)");
            String loaction = scanner.nextLine();

            if (!selectedCard.stampLocation(loaction)) {
                System.out.println("Location: " + loaction + " is Not a Valid Location");
                scanner.nextLine();
                continue;
            }
        }
    }

    // Holds all the terminal prompt messages and checks corresponding to a manualMode game.
    private void manualMode(Scanner scanner) {
        System.out.println("Input the First Location to Call: ");
        String userInput = scanner.nextLine();
        String callerValue = userInput;

        while (!maualCaller(callerValue)) {
            System.out.println("Value: " + callerValue + " is an Invalid Caller Value");
            callerValue = scanner.nextLine();
        }

        while (true) {
            System.out.println("\n");
            drawCards(userCards);
            System.out.println("Called Position: " + callerValue + "\n");

            System.out.println("Enter \"Bingo\" to check for Bingo.");
            System.out.println("Enter \"Call\" to call a new Location.");
            System.out.println("Or you can Stamp a Card. (CardName or Index): ");
            userInput = scanner.nextLine();

            if (userInput.equals("Bingo")) {
                CardHandler[] bingos = returnBingos();
                if (bingos.length > 0) {
                    System.out.println("\n\nBingos Were Found on the Following Cards:");
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
                    System.out.println("Value: " + callerValue + " is an Invalid Caller Value or Has Already Been Called. Please enter a Location to Call: ");
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

            System.out.println("\n");
            drawCard(selectedCard);
            System.out.println("Called Position: " + callerValue + "\n");

            System.out.println("What Position Would you Like to Stamp? (B-OB-O/B-0#)");
            String loaction = scanner.nextLine();

            if (!selectedCard.stampLocation(loaction)) {
                System.out.println("Location: " + loaction + " is Not a Valid Location");
                scanner.nextLine();
                continue;
            }
        }
    }

    // Holds all the terminal prompt messages and checks so that either game mode can start correctly. (Sets amount of cards and asks what gamemode to use).
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
            randomMode(scanner);
        }

        if (userInput.equals("Manual")) {
            manualSelectUserCards(scanner, cardCount);
            manualMode(scanner);
        }

        scanner.close();
    }   
}