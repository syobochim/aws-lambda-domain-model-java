package reservation;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class RecipientTest {

    String recipientId = "1";
    String email = "mugajin@example.com";
    String firstName = "Mizuki";
    String lastName = "Ugajin";
    int age = 30;
    LocalDateTime datetimeSlot = LocalDateTime.of(2021, 11, 12, 10, 0, 0);
    LocalDateTime datetimeSlot2 = LocalDateTime.of(2021, 12, 10, 10, 0, 0);
    LocalDateTime datetimeSlot3 = LocalDateTime.of(2021, 12, 31, 10, 0, 0);
    String location = "Tokyo";

    Slot slot = new Slot("1", datetimeSlot, location);
    Slot slot2 = new Slot("2", datetimeSlot2, location);
    Slot slot3 = new Slot("3", datetimeSlot3, location);
    Slot slotSameDatetime = new Slot("4", datetimeSlot, "Osaka");

    @Test
    public void testNewRecipient() {
        Recipient sut = new Recipient(recipientId, email, firstName, lastName, age);
        assertEquals(recipientId, sut.recipientId);
        assertEquals(email, sut.email);
        assertEquals(firstName, sut.firstName);
        assertEquals(lastName, sut.lastName);
        assertEquals(age, sut.age);
        assertNotNull(sut.getSlots());
        assertEquals(0, sut.getSlots().size());
    }

    @Test
    public void testAddSlotOne() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        assertNotNull(target.getSlots());
        assertEquals(1, target.getSlots().size());
        assertEquals(this.slot.slotId, target.getSlots().get(0).slotId);
        assertEquals(this.slot.reservationDate, target.getSlots().get(0).reservationDate);
        assertEquals(this.slot.location, target.getSlots().get(0).location);
        assertFalse(target.getSlots().get(0).isVacant());
    }

    @Test
    public void testAddSlotTwo() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        target.addReserveSlot(slot2);
        assertEquals(2, target.getSlots().size());
    }

    @Test
    public void testCannotAppendSlotMoreThanTwo() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        target.addReserveSlot(slot2);
        assertFalse(target.addReserveSlot(slot3));
        assertEquals(2, target.getSlots().size());
    }

    @Test
    public void testCannotAppendSameDateSlot() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        assertFalse(target.addReserveSlot(slotSameDatetime));
        assertEquals(1, target.getSlots().size());
    }
}