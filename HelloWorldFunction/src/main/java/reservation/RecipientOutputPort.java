package reservation;

/**
 * @author mugajin
 */
public class RecipientOutputPort implements RecipientOutputPortInterface {
    private final RecipientAdapterInterface recipientAdapter;

    public RecipientOutputPort(RecipientAdapterInterface recipientAdapter) {
        this.recipientAdapter = recipientAdapter;
    }

    @Override
    public Recipient recipientById(String recipientId) {
        return recipientAdapter.load(recipientId);
    }

    @Override
    public boolean addReservation(Recipient recipient) {
        return recipientAdapter.save(recipient);
    }
}
