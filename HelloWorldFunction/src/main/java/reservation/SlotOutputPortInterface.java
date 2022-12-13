package reservation;

/**
 * @author mugajin
 */
public interface SlotOutputPortInterface {
    Slot slotById(String slotId);
    boolean updateSlotStatus(Slot slot);
}
