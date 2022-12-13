package reservation;

/**
 * @author mugajin
 */
public interface SlotAdapterInterface {
    Slot load(String slotId);
    boolean save(Slot slot);
}
