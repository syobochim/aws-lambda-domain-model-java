package reservation;

/**
 * @author mugajin
 */
public interface RecipientInputPortInterface {
    Status makeReservation(String recipientId, String slotId);
}
