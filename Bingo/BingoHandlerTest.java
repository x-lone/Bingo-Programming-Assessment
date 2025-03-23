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
                bingoHandler.allCards[1].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }

        assertEquals(3, bingoHandler.countBingos(bingoHandler.allCards[1]));
    }
    @Test
    public void BM3() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");

        for (int y = 0; y < 5; y+=2) {
            for (int x = 0; x < 5; x++) {
                bingoHandler.allCards[2].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }
        for (int y = 0; y < 5; y++) {
            bingoHandler.allCards[2].stampLocation("O"+"BINGO".charAt(y));
        }

        assertEquals(3, bingoHandler.countBingos(bingoHandler.allCards[2]));
    }
    @Test
    public void BM4() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");
        bingoHandler.addPattern("Column Pattern");

        for (int y = 0; y < 5; y+=2) {
            for (int x = 0; x < 5; x++) {
                bingoHandler.allCards[3].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }
        for (int y = 0; y < 5; y++) {
            bingoHandler.allCards[3].stampLocation("O"+"BINGO".charAt(y));
        }

        assertEquals(4, bingoHandler.countBingos(bingoHandler.allCards[3]));
    }
    @Test
    public void BM5() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");
        bingoHandler.addPattern("Column Pattern");
        bingoHandler.addPattern("Diagonal Pattern");

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                bingoHandler.allCards[4].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }

        assertEquals(12, bingoHandler.countBingos(bingoHandler.allCards[4]));
    }
}
