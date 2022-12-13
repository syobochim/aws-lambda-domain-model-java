package reservation;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author mugajin
 */
public class RecipientInputPortTest {
    String email = "mugajin@example.com";
    String firstName = "Mizuki";
    String lastName = "Ugajin";
    int age = 32;
    LocalDateTime reservationDate = LocalDateTime.of(2021, 12, 20, 9, 0);
    String location = "Tokyo";

    @Test
    public void testAddReservation() {
        RecipientInputPort sut = new RecipientInputPort(new DummyRecipientOutputPort(), new DummySlotOutputPort());
        Status status = sut.makeReservation("dummy_reservation_id", "dummy_slot_id");
        assertEquals(200, status.statusCode);
        assertEquals("The recipient's reservation is added.", status.message);
    }

    class DummyRecipientOutputPort implements RecipientOutputPortInterface {
        @Override
        public Recipient recipientById(String recipientId) {
            return new Recipient(recipientId, email, firstName, lastName, age);
        }

        @Override
        public boolean addReservation(Recipient recipient) {
            return true;
        }
    }

    class DummySlotOutputPort implements SlotOutputPortInterface {
        @Override
        public Slot slotById(String slotId) {
            return new Slot(slotId, reservationDate, location);
        }

        @Override
        public boolean updateSlotStatus(Slot slot) {
            return true;
        }
    }
}