package Bingo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BingoHandlerTest {
    @Test
    public void BM1() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");

        bingoHandler.allCards[0].stampLocation("BB");
        bingoHandler.allCards[0].stampLocation("IB");
        bingoHandler.allCards[0].stampLocation("NB");
        bingoHandler.allCards[0].stampLocation("GB");
        bingoHandler.allCards[0].stampLocation("OB");

        assertEquals(1, bingoHandler.countBingos(bingoHandler.allCards[0]));
    }
}
