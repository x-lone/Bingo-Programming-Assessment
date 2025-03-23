package Bingo;

import org.junit.Test;

public class BingoHandlerTest {
    @Test
    public void BM1() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");

    }
}
