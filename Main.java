//   Author: Xyrus Lonewolf
//  Started: Feb 3, 2025
// Finished: Feb 18, 2025

import Bingo.GameHander;

public class Main {
    public static void main(String[] args) {
        GameHander game = new GameHander();

        if (game.createCardsFromTxt("BingoCards.txt")) {
            game.run();
        } else {
            System.out.println("There was an issue with the input path. Perhaps is doesn't exist or it's formated incorrectly.");
        }
    }
}