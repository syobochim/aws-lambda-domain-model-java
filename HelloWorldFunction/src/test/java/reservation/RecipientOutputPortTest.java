package reservation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mugajin
 */
public class RecipientOutputPortTest {

    String recipientId = "1";
    String email = "mugajin@example.com";
    String firstName = "Mizuki";
    String lastName = "Ugajin";
    int age = 32;

    class DummyRecipientAdapter implements RecipientAdapterInterface {
        @Override
        public Recipient load(String recipientId) {
            return new Recipient(recipientId, email, firstName, lastName, age);
        }

        @Override
        public boolean save(Recipient recipient) {
            return true;
        }
    }

    @Test
    public void testRecipientPortRecipientById() {
        RecipientOutputPort sut = new RecipientOutputPort(new DummyRecipientAdapter());
        Recipient recipient = sut.recipientById(recipientId);
        assertEquals(recipientId, recipient.recipientId);
        assertEquals(email, recipient.email);
        assertEquals(firstName, recipient.firstName);
        assertEquals(lastName, recipient.lastName);
        assertEquals(age, recipient.age);
    }

    @Test
    public void testRecipientPortAddReservationMustBeTrue() {
        RecipientOutputPort sut = new RecipientOutputPort(new DummyRecipientAdapter());
        assertTrue(sut.addReservation(new Recipient(recipientId, email, firstName, lastName, age)));
    }

}