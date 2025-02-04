package Bingo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameHander {
    public CardHandler[] cards;

    public GameHander(String path) {
        cards = new CardHandler[1];
        int[][] values = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        cards[0] = new CardHandler("Card1", values);
        cards[0].DrawCard();
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

    public void run() {

    }
}

// try {
//     BufferedReader reader = new BufferedReader(new FileReader(path));
//     String line;
//     while ((line = reader.readLine()) != null) {
//         System.out.println(line);
//     }
//     reader.close();
// } catch (IOException ex) {
//     ex.printStackTrace();
// }