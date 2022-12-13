package reservation;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author mugajin
 */
public class SlotOutputPortTest {

    LocalDateTime reservationDate = LocalDateTime.of(2021, 12, 20, 9, 0, 0);
    String location = "Tokyo";

    @Test
    public void testSlotOutputPortSlotById() {
        SlotOutputPort sut = new SlotOutputPort(new DummySlotAdapter());
        String slotId = "1";
        Slot slot = sut.slotById(slotId);
        assertEquals(slotId, slot.slotId);
        assertEquals(reservationDate, slot.reservationDate);
        assertEquals(location, slot.location);
    }

    @Test
    public void testSlotOutputPortSlotSave() {
        SlotOutputPort sut = new SlotOutputPort(new DummySlotAdapter());
        String slotId = "1";
        assertTrue(sut.updateSlotStatus(new Slot(slotId, reservationDate, location)));
    }

    class DummySlotAdapter implements SlotAdapterInterface {
        @Override
        public Slot load(String slotId) {
            return new Slot(slotId, reservationDate, location);
        }

        @Override
        public boolean save(Slot slot) {
            return true;
        }
    }

}