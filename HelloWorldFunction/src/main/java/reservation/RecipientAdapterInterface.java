package reservation;

/**
 * @author mugajin
 */
public interface RecipientAdapterInterface {
    Recipient load(String recipientId);
    boolean save(Recipient recipient);
}
