package Bingo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Game {
    public Card[] cards;

    public Game(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void InitializeCards(int cardCount) {
        cards = new Card[cardCount];
        for (int i = 0; i < cardCount; i++) {
            cards[i] = new Card();
        }
    }

    public void run() {

    }
}
