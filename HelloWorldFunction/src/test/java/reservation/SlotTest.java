package reservation;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author mugajin
 */
public class SlotTest {

    String slotId = "1";
    LocalDateTime datetimeSlot = LocalDateTime.of(2021, 11, 12, 10, 0, 0);
    String location = "Tokyo";

    @Test
    public void testNewSlot() {
        Slot sut = new Slot(slotId, datetimeSlot, location);
        assertEquals(slotId, sut.slotId);
        assertEquals(datetimeSlot, sut.reservationDate);
        assertEquals(location, sut.location);
        assertTrue(sut.isVacant());
    }

    @Test
    public void testUseSlot() {
        Slot sut = new Slot(slotId, datetimeSlot, location);
        assertTrue(sut.isVacant());
        sut.useSlot();
        assertFalse(sut.isVacant());
    }
}
