package Bingo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PatternTest {
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
    @Test
    public void P4() {
        Pattern pattern = new Pattern("Row Pattern");
        CardHandler card = new CardHandler("card4", "12,23,31,48,62,7,19,32,60,75,3,21,45,57,73,8,30,44,58,65,9,22,35,50,68");        

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        
        assertEquals(true, pattern.checkCard(card));
    }
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
    @Test
    public void P8() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card9", "5,22,37,47,65,7,21,33,59,71,4,26,45,58,66,10,27,41,60,68,11,25,35,57,64");

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        card.stampLocation("OB");
        
        assertEquals(true, pattern.checkCard(card));
    }
    @Test
    public void P9() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

        card.stampLocation("IB");
        card.stampLocation("IN");
        card.stampLocation("IG");
        card.stampLocation("IO");
        
        assertEquals(true, pattern.checkCard(card));
    }
}
