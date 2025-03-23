//   Author: Xyrus Lonewolf
//  Started: Feb 3, 2025
// Finished: Mar 23, 2025

import Bingo.BingoHandler;

public class Main {
    public static void main(String[] args) {
        BingoHandler game = new BingoHandler();

        if (game.createCardsFromTxt("BingoCards.txt")) {
            game.addPattern("Row Pattern");
            game.addPattern("Column Pattern");
            game.addPattern("Diagonal Pattern");
            game.run();
        } else {
            System.out.println("There was an issue with the input path. Perhaps is doesn't exist or it's formated incorrectly.");
        }
    }
}