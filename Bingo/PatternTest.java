package Bingo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// Unit tests to ensure the methods of Pattern works as expected. 
public class PatternTest {
    // Tests Row Pattern > Expected Output: true
    // X X X X X
    // O O O O O
    // O O O O O
    // O O O O O
    // O O O O O
    @Test
    public void P1() {
        Pattern pattern = new Pattern("Row Pattern");
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        card.stampLocation("OB");
        
        assertEquals(true, pattern.checkCard(card));
    }

    // Tests Row Pattern > Expected Output: true
    // O O O O O
    // O O O O O
    // X X X X X
    // O O O O O
    // O O O O O
    @Test
    public void P2() {
        Pattern pattern = new Pattern("Row Pattern");
        CardHandler card = new CardHandler("Card2", "9,17,33,56,68,6,30,34,46,70,7,26,37,58,66,5,25,39,47,62,13,22,44,57,72");

        card.stampLocation("BN");
        card.stampLocation("IN");
        card.stampLocation("NN");
        card.stampLocation("GN");
        card.stampLocation("ON");
        
        assertEquals(true, pattern.checkCard(card));
    }

    // Tests Row Pattern > Expected Output: true
    // O O O O O
    // O O O O O
    // O O O O O
    // O O O O O
    // X X X X X
    @Test
    public void P3() {
        Pattern pattern = new Pattern("Row Pattern");
        CardHandler card = new CardHandler("card3", "10,27,40,53,70,5,23,44,54,73,7,26,39,56,66,9,20,45,59,72,15,30,36,58,75");

        card.stampLocation("BO");
        card.stampLocation("IO");
        card.stampLocation("NO");
        card.stampLocation("GO");
        card.stampLocation("OO");
        
        assertEquals(true, pattern.checkCard(card));
    }

    // Tests Row Pattern > Expected Output: false
    // X X X X O
    // O O O O O
    // O O O O O
    // O O O O O
    // O O O O O
    @Test
    public void P4() {
        Pattern pattern = new Pattern("Row Pattern");
        CardHandler card = new CardHandler("card4", "12,23,31,48,62,7,19,32,60,75,3,21,45,57,73,8,30,44,58,65,9,22,35,50,68");        

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        
        assertEquals(false, pattern.checkCard(card));
    }

    // Tests Column Pattern > Expected Output: true
    // X O O O O
    // X O O O O
    // X O O O O
    // X O O O O
    // X O O O O
    @Test
    public void P5() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card5", "11,27,44,60,64,5,26,38,54,73,15,17,41,55,70,2,29,43,51,74,3,18,39,48,63");

        card.stampLocation("BB");
        card.stampLocation("BI");
        card.stampLocation("BN");
        card.stampLocation("BG");
        card.stampLocation("BO");
        
        assertEquals(true, pattern.checkCard(card));
    }
    
    // Tests Column Pattern > Expected Output: true
    // O O O X O
    // O O O X O
    // O O O X O
    // O O O X O
    // O O O X O
    @Test
    public void P6() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card7", "14,27,43,47,63,3,18,34,51,72,9,16,36,57,70,8,28,37,54,66,12,29,40,52,68");

        card.stampLocation("GB");
        card.stampLocation("GI");
        card.stampLocation("GN");
        card.stampLocation("GG");
        card.stampLocation("GO");
        
        assertEquals(true, pattern.checkCard(card));
    }

    // Tests Column Pattern > Expected Output: true
    // O O O O X
    // O O O O X
    // O O O O X
    // O O O O X
    // O O O O X
    @Test
    public void P7() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card8", "13,20,35,58,69,3,22,39,54,74,1,17,34,55,75,2,25,45,57,73,11,21,31,52,70");

        card.stampLocation("OB");
        card.stampLocation("OI");
        card.stampLocation("ON");
        card.stampLocation("OG");
        card.stampLocation("OO");
        
        assertEquals(true, pattern.checkCard(card));
    }

    // Tests Column Pattern > Expected Output: false
    // X X X X X
    // O O O O O
    // O O O O O
    // O O O O O
    // O O O O O
    @Test
    public void P8() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card9", "5,22,37,47,65,7,21,33,59,71,4,26,45,58,66,10,27,41,60,68,11,25,35,57,64");

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        card.stampLocation("OB");
        
        assertEquals(false, pattern.checkCard(card));
    }

    // Tests Column Pattern > Expected Output: false
    // X X X X X
    // O O O O O
    // O O O O O
    // O O O O O
    // O O O O O
    @Test
    public void P9() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

        card.stampLocation("IB");
        card.stampLocation("IN");
        card.stampLocation("IG");
        card.stampLocation("IO");
        
        assertEquals(false, pattern.checkCard(card));
    }

    // Tests Diagonal Pattern > Expected Output: true
    // O O O O X
    // O O O X O
    // O O X O O
    // O X O O O
    // X O O O O
    @Test
    public void P10() {
        Pattern pattern = new Pattern("Diagonal Pattern");
        CardHandler card = new CardHandler("Card2", "9,17,33,56,68,6,30,34,46,70,7,26,37,58,66,5,25,39,47,62,13,22,44,57,72");

        card.stampLocation("BB");
        card.stampLocation("II");
        card.stampLocation("NN");
        card.stampLocation("GG");
        card.stampLocation("OO");
        
        assertEquals(true, pattern.checkCard(card));
    }

    // Tests Diagonal Pattern > Expected Output: true
    // X O O O O
    // O X O O O
    // O O X O O
    // O O O X O
    // O O O O X
    @Test
    public void P11() {
        Pattern pattern = new Pattern("Diagonal Pattern");
        CardHandler card = new CardHandler("card3", "10,27,40,53,70,5,23,44,54,73,7,26,39,56,66,9,20,45,59,72,15,30,36,58,75");

        card.stampLocation("OB");
        card.stampLocation("GI");
        card.stampLocation("NN");
        card.stampLocation("IG");
        card.stampLocation("BO");
        
        assertEquals(true, pattern.checkCard(card));
    }
    
    // Tests Diagonal Pattern > Expected Output: false
    // O O O O O
    // X X X X X
    // O O O O O
    // O O O O O
    // O O O O O
    @Test
    public void P12() {
        Pattern pattern = new Pattern("Diagonal Pattern");
        CardHandler card = new CardHandler("card4", "12,23,31,48,62,7,19,32,60,75,3,21,45,57,73,8,30,44,58,65,9,22,35,50,68");

        card.stampLocation("BI");
        card.stampLocation("II");
        card.stampLocation("NI");
        card.stampLocation("GI");
        card.stampLocation("OI");
        
        assertEquals(false, pattern.checkCard(card));
    }
    
    // Tests Custom Pattern: Toprow & Middle Column (the "T") > Expected Output: true
    // X X X X X
    // O O X O O
    // O O X O O
    // O O X O O
    // O O X O O
    @Test
    public void P13() {
        int[][] customPattern = {{0,0},{1,0},{2,0},{3,0},{4,0},{2,1},{2,2},{2,3},{2,4}};
        Pattern pattern = new Pattern(customPattern);
        CardHandler card = new CardHandler("card5", "11,27,44,60,64,5,26,38,54,73,15,17,41,55,70,2,29,43,51,74,3,18,39,48,63");

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        card.stampLocation("OB");
        card.stampLocation("NI");
        card.stampLocation("NN");
        card.stampLocation("NG");
        card.stampLocation("NO");
        
        assertEquals(true, pattern.checkCard(card));
    }
    
    // Tests Custom Pattern: Toprow & Middle Column (the "T") > Expected Output: true
    // X X X X X
    // X X X X X
    // X X X X X
    // X X X X X
    // X X X X X
    @Test
    public void P14() {
        int[][] customPattern = {{0,0},{1,0},{2,0},{3,0},{4,0},{2,1},{2,2},{2,3},{2,4}};
        Pattern pattern = new Pattern(customPattern);
        CardHandler card = new CardHandler("card7", "14,27,43,47,63,3,18,34,51,72,9,16,36,57,70,8,28,37,54,66,12,29,40,52,68");

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                card.stampLocation(""+"BINGO".charAt(y)+"BINGO".charAt(x));
            }
        }
        
        assertEquals(true, pattern.checkCard(card));
    }
    
    // Tests Custom Pattern: Toprow & Middle Column (the "T") > Expected Output: false
    // X X X X X
    // X X X X X
    // X X O X X
    // X X X X X
    // X X X X X
    @Test
    public void P15() {
        int[][] customPattern = {{0,0},{1,0},{2,0},{3,0},{4,0},{2,1},{2,2},{2,3},{2,4}};
        Pattern pattern = new Pattern(customPattern);
        CardHandler card = new CardHandler("card8", "13,20,35,58,69,3,22,39,54,74,1,17,34,55,75,2,25,45,57,73,11,21,31,52,70");

        for (int x = 0; x < 5; x++) {
            card.stampLocation(""+"BINGO".charAt(x)+"B");
        }
        card.stampLocation("NO");
        
        assertEquals(false, pattern.checkCard(card));
    }
    
    // Tests Custom Pattern: top row, bottom row, left column, and right column (a square) > Expected Output: true
    // X X X X X
    // X O O O X
    // X O O O X
    // X O O O X
    // X X X X X
    @Test
    public void P16() {
        int[][] customPattern = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,4},{1,4},{2,4},{3,4},{4,4},{0,1},{0,2},{0,3},{4,1},{4,2},{4,3}};
        Pattern pattern = new Pattern(customPattern);
        CardHandler card = new CardHandler("card9", "5,22,37,47,65,7,21,33,59,71,4,26,45,58,66,10,27,41,60,68,11,25,35,57,64");

        for (int x = 0; x < 5; x++) {
            card.stampLocation(""+"BINGO".charAt(x)+"B");
            card.stampLocation(""+"BINGO".charAt(x)+"O");
        }
        for (int y = 1; y < 4; y++) {
            card.stampLocation("B"+"BINGO".charAt(y));
            card.stampLocation("O"+"BINGO".charAt(y));
        }
        
        assertEquals(true, pattern.checkCard(card));
    }
        
    // Tests Custom Pattern: top row, bottom row, left column, and right column (a square) > Expected Output: false
    // X X X X X
    // X X X X X
    // X X X X X
    // X X X X X
    // X X X X O
    @Test
    public void P17() {
        int[][] customPattern = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,4},{1,4},{2,4},{3,4},{4,4},{0,1},{0,2},{0,3},{4,1},{4,2},{4,3}};
        Pattern pattern = new Pattern(customPattern);
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

        for (int x = 0; x < 5; x++) {
            card.stampLocation(""+"BINGO".charAt(x)+"B");
            card.stampLocation(""+"BINGO".charAt(x)+"I");
            card.stampLocation(""+"BINGO".charAt(x)+"N");
            card.stampLocation(""+"BINGO".charAt(x)+"G");
        }
        for (int x = 0; x < 4; x++) {
            card.stampLocation(""+"BINGO".charAt(x)+"O");
        }
        
        assertEquals(false, pattern.checkCard(card));
    }
        
    // Tests Custom Pattern: top row, bottom row, left column, and right column (a square) > Expected Output: false
    // O X X X X
    // X X X X X
    // X X X X X
    // X X X X X
    // X X X X X
    @Test
    public void P18() {
        int[][] customPattern = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,4},{1,4},{2,4},{3,4},{4,4},{0,1},{0,2},{0,3},{4,1},{4,2},{4,3}};
        Pattern pattern = new Pattern(customPattern);
        CardHandler card = new CardHandler("Card2", "9,17,33,56,68,6,30,34,46,70,7,26,37,58,66,5,25,39,47,62,13,22,44,57,72");

        for (int x = 1; x < 5; x++) {
            card.stampLocation(""+"BINGO".charAt(x)+"B");
        }
        for (int x = 0; x < 5; x++) {
            card.stampLocation(""+"BINGO".charAt(x)+"I");
            card.stampLocation(""+"BINGO".charAt(x)+"N");
            card.stampLocation(""+"BINGO".charAt(x)+"G");
            card.stampLocation(""+"BINGO".charAt(x)+"O");
        }
        
        assertEquals(false, pattern.checkCard(card));
    }
}
