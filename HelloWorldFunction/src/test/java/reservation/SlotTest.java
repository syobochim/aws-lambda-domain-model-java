package reservation;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author mugajin
 */
public class SlotTest {

    String slotId = "1";
    LocalDateTime dtSlot = LocalDateTime.of(2021, 11, 12, 10, 0, 0);
    String location = "Tokyo";

    @Test
    public void testNewSlog() {
        Slot target = new Slot(slotId, dtSlot, location);
        assertEquals(slotId, target.slotId);
        assertEquals(dtSlot, target.reservationDate);
        assertEquals(location, target.location);
        assertTrue( target.isVacant);
    }

    @Test
    public void testUseSlot() {
        Slot target = new Slot(slotId, dtSlot, location);
        assertTrue(target.isVacant);
        target.useSlot();
        assertFalse(target.isVacant);
    }
}
