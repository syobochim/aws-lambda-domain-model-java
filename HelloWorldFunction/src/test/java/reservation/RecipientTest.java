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
    LocalDateTime dtSlot = LocalDateTime.of(2021, 11, 12, 10, 0, 0);
    LocalDateTime dtSlot2 = LocalDateTime.of(2021, 12, 10, 10, 0, 0);
    LocalDateTime dtSlot3 = LocalDateTime.of(2021, 12, 31, 10, 0, 0);
    String location = "Tokyo";

    Slot slot = new Slot("1", dtSlot, location);
    Slot slot2 = new Slot("2", dtSlot2, location);
    Slot slot3 = new Slot("3", dtSlot3, location);

    @Test
    public void testNewRecipient() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        assertEquals(recipientId, target.recipientId);
        assertEquals(email, target.email);
        assertEquals(firstName, target.firstName);
        assertEquals(lastName, target.lastName);
        assertEquals(age, target.age);
        assertNotNull(target.slots);
        assertEquals(0, target.slots.size());
    }

    @Test
    public void testAddSlotOne() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        assertNotNull(target.slots);
        assertEquals(1, target.slots.size());
        assertEquals(this.slot.slotId, target.slots.get(0).slotId);
        assertEquals(this.slot.reservationDate, target.slots.get(0).reservationDate);
        assertEquals(this.slot.location, target.slots.get(0).location);
        assertFalse(target.slots.get(0).isVacant);
    }

    @Test
    public void testAddSlotTwo() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        target.addReserveSlot(slot2);
        assertEquals(2, target.slots.size());
    }

    @Test
    public void testCannotAppendSlotMoreThanTwo() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        target.addReserveSlot(slot2);
        assertFalse(target.addReserveSlot(slot3));
        assertEquals(2, target.slots.size());
    }

    @Test
    public void testCannotAppendSameDateSlot() {
        Recipient target = new Recipient(recipientId, email, firstName, lastName, age);
        target.addReserveSlot(slot);
        assertFalse(target.addReserveSlot(slot));
        assertEquals(1, target.slots.size());
    }
}