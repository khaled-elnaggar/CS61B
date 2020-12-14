import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        assertFalse(Flik.isSameNumber(4, 1));
        assertFalse(Flik.isSameNumber(4, -8));
        assertTrue(Flik.isSameNumber(-4, -4));
        assertTrue(Flik.isSameNumber(99999, 99999));

    }
}
