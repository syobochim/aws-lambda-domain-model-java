package reservation;

/**
 * @author mugajin
 */
public class RecipientInputPort implements RecipientInputPortInterface {
    private final RecipientOutputPortInterface recipientOutputPort;
    private final SlotOutputPortInterface slotOutputPort;

    public RecipientInputPort(RecipientOutputPortInterface recipientOutputPort, SlotOutputPortInterface slotOutputPort) {
        this.recipientOutputPort = recipientOutputPort;
        this.slotOutputPort = slotOutputPort;
    }

    public Status makeReservation(String recipientId, String slotId) {
        Recipient recipient = recipientOutputPort.recipientById(recipientId);
        Slot slot = slotOutputPort.slotById(slotId);
        if (recipient == null || slot == null) {
            return new Status(400, "Request instance is not found. Something wrong!");
        }

        System.out.println("recipient: " + recipient.firstName + " slotDate:  " + slot.reservationDate);

        boolean isSuccessAdd = recipient.addReserveSlot(slot);
        if (isSuccessAdd) {
            recipientOutputPort.addReservation(recipient);
            return new Status(200, "The recipient's reservation is added.");
        }
        return new Status(200, "The recipient's reservation is NOT added!");
    }
}
