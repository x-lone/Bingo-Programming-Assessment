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
    @Test
    public void BM2() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");

        for (int y = 0; y < 5; y+=2) {
            for (int x = 0; x < 5; x++) {
                bingoHandler.allCards[0].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }

        assertEquals(3, bingoHandler.countBingos(bingoHandler.allCards[0]));
    }
    @Test
    public void BM3() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");



        assertEquals(3, bingoHandler.countBingos(bingoHandler.allCards[0]));
    }
}
