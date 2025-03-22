package Bingo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

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
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

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
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        
        assertEquals(true, pattern.checkCard(card));
    }
    @Test
    public void P5() {
        Pattern pattern = new Pattern("Column Pattern");
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

        card.stampLocation("BB");
        card.stampLocation("BI");
        card.stampLocation("BN");
        card.stampLocation("BG");
        card.stampLocation("BO");
        
        assertEquals(true, pattern.checkCard(card));
    }
}
