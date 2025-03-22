package Bingo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PatternTest {
    @Test
    public void rowPattern() {
        Pattern pattern = new Pattern("Row Pattern");
        CardHandler card = new CardHandler("card1", "3,22,32,51,66,6,19,44,60,61,7,27,45,58,72,15,16,48,46,75,1,30,31,52,63");

        card.stampLocation("BB");
        card.stampLocation("IB");
        card.stampLocation("NB");
        card.stampLocation("GB");
        card.stampLocation("OB");
        
        assertEquals(true, pattern.checkCard(card));
    }
}
