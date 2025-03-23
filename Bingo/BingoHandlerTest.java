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
    @Test
    public void BM6() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");
        bingoHandler.addPattern("Column Pattern");
        bingoHandler.addPattern("Diagonal Pattern");
        int[][] customPatternT = {{0,0},{1,0},{2,0},{3,0},{4,0},{2,1},{2,2},{2,3},{2,4}};
        bingoHandler.addPattern(customPatternT);
        int[][] customPatternSquare = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,4},{1,4},{2,4},{3,4},{4,4},{0,1},{0,2},{0,3},{4,1},{4,2},{4,3}};
        bingoHandler.addPattern(customPatternSquare);

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                bingoHandler.allCards[5].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }

        assertEquals(14, bingoHandler.countBingos(bingoHandler.allCards[5]));
    }
    @Test
    public void BM7() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");
        bingoHandler.addPattern("Column Pattern");
        bingoHandler.addPattern("Diagonal Pattern");
        int[][] customPatternT = {{0,0},{1,0},{2,0},{3,0},{4,0},{2,1},{2,2},{2,3},{2,4}};
        bingoHandler.addPattern(customPatternT);
        int[][] customPatternSquare = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,4},{1,4},{2,4},{3,4},{4,4},{0,1},{0,2},{0,3},{4,1},{4,2},{4,3}};
        bingoHandler.addPattern(customPatternSquare);

        for (int x = 0; x < 4; x++) {
            bingoHandler.allCards[6].stampLocation(""+"BINGO".charAt(x)+"B");
        }
        for (int y = 1; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                bingoHandler.allCards[6].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }

        assertEquals(9, bingoHandler.countBingos(bingoHandler.allCards[6]));
    }
    @Test
    public void BM8() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");
        bingoHandler.addPattern("Column Pattern");
        bingoHandler.addPattern("Diagonal Pattern");
        int[][] customPatternT = {{0,0},{1,0},{2,0},{3,0},{4,0},{2,1},{2,2},{2,3},{2,4}};
        bingoHandler.addPattern(customPatternT);
        int[][] customPatternSquare = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,4},{1,4},{2,4},{3,4},{4,4},{0,1},{0,2},{0,3},{4,1},{4,2},{4,3}};
        bingoHandler.addPattern(customPatternSquare);

        for (int x = 0; x < 5; x++) {
            bingoHandler.allCards[7].stampLocation(""+"BINGO".charAt(x)+"B");
        }
        for (int y = 2; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                bingoHandler.allCards[7].stampLocation(""+"BINGO".charAt(x)+"BINGO".charAt(y));
            }
        }
        bingoHandler.allCards[7].stampLocation("BI");
        for (int x = 2; x < 5; x++) {
            bingoHandler.allCards[7].stampLocation(""+"BINGO".charAt(x)+"I");
        }

        assertEquals(11, bingoHandler.countBingos(bingoHandler.allCards[7]));
    }
    @Test
    public void BM9() {
        BingoHandler bingoHandler = new BingoHandler();
        bingoHandler.createCardsFromTxt("BingoCards.txt");
        bingoHandler.addPattern("Row Pattern");
        bingoHandler.addPattern("Column Pattern");
        bingoHandler.addPattern("Diagonal Pattern");
        int[][] customPatternT = {{0,0},{1,0},{2,0},{3,0},{4,0},{2,1},{2,2},{2,3},{2,4}};
        bingoHandler.addPattern(customPatternT);
        int[][] customPatternSquare = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,4},{1,4},{2,4},{3,4},{4,4},{0,1},{0,2},{0,3},{4,1},{4,2},{4,3}};
        bingoHandler.addPattern(customPatternSquare);

        

        assertEquals(12, bingoHandler.countBingos(bingoHandler.allCards[0]));
    }
}
