package reservation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mugajin
 */
public class StatusTest {

    @Test
    public void testSetStatusProperties() {
        int statusCode = 200;
        String message = "hello";

        Status target = new Status(statusCode, message);
        assertEquals(statusCode, target.statusCode);
        assertEquals(message, target.message);
    }

}