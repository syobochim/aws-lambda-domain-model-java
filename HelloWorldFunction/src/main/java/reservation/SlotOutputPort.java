package reservation;

/**
 * @author mugajin
 */
public class SlotOutputPort implements SlotOutputPortInterface {
    final SlotAdapterInterface adapter;

    SlotOutputPort(SlotAdapterInterface slotAdapter) {
        adapter = slotAdapter;
    }

    @Override
    public Slot slotById(String slotId) {
        return adapter.load(slotId);
    }

    @Override
    public boolean updateSlotStatus(Slot slot) {
        return adapter.save(slot);
    }
}
