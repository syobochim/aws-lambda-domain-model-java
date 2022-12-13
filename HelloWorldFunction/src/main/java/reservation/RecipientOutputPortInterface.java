package reservation;

/**
 * @author mugajin
 */
public interface RecipientOutputPortInterface {
    Recipient recipientById(String recipientId);
    boolean addReservation(Recipient recipient);
}
